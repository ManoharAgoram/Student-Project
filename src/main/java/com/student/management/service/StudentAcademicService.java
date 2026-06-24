package com.student.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.student.management.dto.StudentAcademicDTO;
import com.student.management.dto.TopStudentDTO;
import com.student.management.entity.Student;
import com.student.management.entity.StudentAcademic;
import com.student.management.repository.StudentAcademicRepository;
import com.student.management.repository.StudentRepository;

@Service
public class StudentAcademicService {
	private final StudentAcademicRepository repo;
	private final StudentRepository studentRepo;

	public StudentAcademicService(StudentAcademicRepository repo, StudentRepository studentRepo) {
		this.repo = repo;
		this.studentRepo = studentRepo;
	}

	public String createAcademic(List<StudentAcademicDTO> dtos) {
		for (StudentAcademicDTO dto : dtos) {

			Student student = studentRepo.findByStudentId(dto.getStudentId());

			if (student == null) {
				throw new RuntimeException("Student Not Found : " + dto.getStudentId());
			}

			StudentAcademic academic = new StudentAcademic();

			academic.setCgpa(dto.getCgpa());
			academic.setArrearsCount(dto.getArrearsCount());
			academic.setArrearsCleared(dto.getArrearsCleared());

			academic.setStudent(student);

			repo.save(academic);
		}

		return "Academic Record Added Successfully";
	}

	public String updateAcademic(String studentId, StudentAcademicDTO dto) {
		StudentAcademic academic = repo.findByStudentStudentId(studentId);

		if (academic == null) {
			throw new RuntimeException("Academic Record not found");
		}

		academic.setCgpa(dto.getCgpa());
		academic.setArrearsCount(dto.getArrearsCount());
		academic.setArrearsCleared(dto.getArrearsCleared());

		repo.save(academic);

		return "Academic Record Updated Successfully";
	}

	public String deleteAcademic(String studentId) {
		StudentAcademic academic = repo.findByStudentStudentId(studentId);

		if (academic == null) {
			throw new RuntimeException("Academic Record is not found");
		}

		repo.delete(academic);

		return "Academic Record Deleted Successfully";
	}

	public StudentAcademic getAcademic(String studentId) {

		return repo.findByStudentStudentId(studentId);
	}

	public List<TopStudentDTO> getTopCgpaStudent() {
		List<StudentAcademic> academics = repo.findAllByOrderByCgpaDesc();

		List<TopStudentDTO> result = new ArrayList<>();

		for (StudentAcademic academic : academics) {

			TopStudentDTO dto = new TopStudentDTO();

			dto.setStudentId(academic.getStudent().getStudentId());

			dto.setFirstName(academic.getStudent().getFirstName());

			dto.setCgpa(academic.getCgpa());

			result.add(dto);
		}
		return result;
	}
	
	public void userDetails() {}

}

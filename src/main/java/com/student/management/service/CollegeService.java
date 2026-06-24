package com.student.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.management.dto.CollegeDTO;
import com.student.management.entity.College;
import com.student.management.entity.Student;
import com.student.management.repository.CollegeRepository;

@Service
public class CollegeService {

	private CollegeRepository repo;

	public CollegeService(CollegeRepository repo) {
		this.repo = repo;
	}

	public String createCollege(List<CollegeDTO> dto) {

		for (CollegeDTO dtos : dto) {
			College college = new College();
			college.setCollegeName(dtos.getCollegeName());
			college.setCollegeCode(dtos.getCollegeCode());
			college.setUniversityName(dtos.getUniversityName());

			repo.save(college);
		}

		return "College Created Successfully";
	}

	public List<College> getAllColleges() {

		return repo.findAll();
	}

	public College getCollegeById(String collegeCode) {

		College college = repo.findByCollegeCode(collegeCode);

		if (college == null) {
			throw new RuntimeException("College Not Found");
		}
		return college;
	}

	public String updateCollege(String collegeCode, CollegeDTO dto) {

		College college = repo.findByCollegeCode(collegeCode);

		college.setCollegeName(dto.getCollegeName());
		college.setCollegeCode(dto.getCollegeCode());
		college.setUniversityName(dto.getUniversityName());

		repo.save(college);

		return "College Updated Successfully";
	}

	public String deleteCollege(Long collegeId) {

		repo.deleteById(collegeId);
		return "College Deleted Successfully";
	}

	public List<Student> getStudentByCollegeId(long collegeId) {

		College college = repo.findById(collegeId)
				.orElseThrow(() -> new RuntimeException("College not found with id : " + collegeId));

		return college.getStudents();
	}

}

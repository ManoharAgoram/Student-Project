package com.student.management.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.student.management.dto.StudentDTO;
import com.student.management.entity.College;
import com.student.management.entity.Student;
import com.student.management.repository.CollegeRepository;
import com.student.management.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository repo;
	private final CollegeRepository collegeRepo;

	public StudentService(StudentRepository repo, CollegeRepository collegeRepo) {
		this.repo = repo;
		this.collegeRepo = collegeRepo;
	}

	public String createStudent(List<StudentDTO> dtos) {

		for (StudentDTO dto : dtos) {

			College college = collegeRepo.findById(dto.getCollegeId())
					.orElseThrow(() -> new RuntimeException("College Not Found"));

			Student student = new Student();
			student.setStudentId(dto.getStudentId());
			student.setFirstName(dto.getFirstName());
			student.setLastName(dto.getLastName());
			student.setDateOfBirth(dto.getDateOfBirth());
			student.setEmail(dto.getEmail());
			student.setDepartment(dto.getDepartment());
			student.setJoinedYear(dto.getJoinedYear());
			student.setPassedOutYear(dto.getPassedOutYear());

			student.setCollege(college);
			repo.save(student);
		}

		return "Student Record Added Successfully";

	}

	public List<Student> getAllStudents() {
		return repo.findAll();
	}

	public Student getStudentById(String studentId) {
		return repo.findByStudentId(studentId);
	}

	public String updateStudentById(String studentId, StudentDTO dto) {

		Student student = repo.findByStudentId(studentId);

		if (student == null) {
			throw new RuntimeException("Student Not Found");
		}

		College college = collegeRepo.findById(dto.getCollegeId())
				.orElseThrow(() -> new RuntimeException("College Not Found"));

		student.setStudentId(dto.getStudentId());
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setEmail(dto.getEmail());
		student.setDateOfBirth(dto.getDateOfBirth());
		student.setDepartment(dto.getDepartment());
		student.setJoinedYear(dto.getJoinedYear());
		student.setPassedOutYear(dto.getPassedOutYear());

		student.setCollege(college);

		repo.save(student);

		return "Student Record Updated Successfully";

	}

	@Transactional
	public String deleteStudent(String studentId) {
		repo.deleteByStudentId(studentId);
		return "Record Deleted Successfully";

	}

	public Page<Student> getStudents(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Student> studentPage = repo.findAll(pageable);
		return studentPage;

	}

	public ByteArrayInputStream exportStudentsToExcel() throws Exception {

		List<Student> students = repo.findAll();

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Students");

		Row header = sheet.createRow(0);

		header.createCell(0).setCellValue("Student Id");

		header.createCell(1).setCellValue("First Name");

		header.createCell(2).setCellValue("Last Name");

		header.createCell(3).setCellValue("Email");

		header.createCell(4).setCellValue("Department");

		int rowNum = 1;

		for (Student student : students) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(student.getStudentId());

			row.createCell(1).setCellValue(student.getFirstName());

			row.createCell(2).setCellValue(student.getLastName());

			row.createCell(3).setCellValue(student.getEmail());

			row.createCell(4).setCellValue(student.getDepartment());

		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		workbook.write(out);

		workbook.close();

		return new ByteArrayInputStream(out.toByteArray());

	}

	@Transactional
	public String importStudentsFromExcel(MultipartFile file) throws Exception {

		Workbook workbook = new XSSFWorkbook(file.getInputStream());

		Sheet sheet = workbook.getSheetAt(0);

		DataFormatter formatter = new DataFormatter();

		for (Row row : sheet) {

			if (row.getRowNum() == 0) {
				continue;
			}

			Student student = new Student();

			student.setStudentId(formatter.formatCellValue(row.getCell(0)));

			student.setFirstName(formatter.formatCellValue(row.getCell(1)));

			student.setLastName(formatter.formatCellValue(row.getCell(2)));

			student.setEmail(formatter.formatCellValue(row.getCell(3)));

			student.setDepartment(formatter.formatCellValue(row.getCell(5)));

			student.setDateOfBirth(readDate(row.getCell(4)));

			student.setJoinedYear(readDate(row.getCell(6)));

			student.setPassedOutYear(readDate(row.getCell(7)));

			long collegeId = (long) row.getCell(8).getNumericCellValue();

			College college = collegeRepo.findById(collegeId)
					.orElseThrow(() -> new RuntimeException("College Not Found : " + collegeId));

			student.setCollege(college);

			repo.save(student);
		}

		workbook.close();

		return "Students Imported Successfully";
	}

	private LocalDate readDate(Cell cell) {

		if (cell == null) {
			return null;
		}

		if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {

			return cell.getLocalDateTimeCellValue().toLocalDate();
		}

		return LocalDate.parse(new DataFormatter().formatCellValue(cell));
	}
	
}

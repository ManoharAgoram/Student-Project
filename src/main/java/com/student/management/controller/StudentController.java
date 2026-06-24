package com.student.management.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.student.management.dto.StudentDTO;
import com.student.management.entity.Student;
import com.student.management.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")

public class StudentController {

	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudents(@RequestBody @Valid List<StudentDTO> dto) {

		String response = service.createStudent(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> result = service.getAllStudents();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@GetMapping("/getStudentById/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {

		Student student = service.getStudentById(studentId);

		if (student != null) {
			return ResponseEntity.status(HttpStatus.OK).body(student);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/updatedStudent/{studentId}")
	public ResponseEntity<String> updateStudent(@PathVariable String studentId, @RequestBody @Valid StudentDTO dto) {
		String response = service.updateStudentById(studentId, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/deleteStudent/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable String studentId) {
		String response = service.deleteStudent(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/allStudents")
	public ResponseEntity<Page<Student>> getStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		Page<Student> response = service.getStudents(page, size);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/export/excel")

	public ResponseEntity<InputStreamResource> exportExcel() throws Exception {
		ByteArrayInputStream file = service.exportStudentsToExcel();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment; filename = students.xlsx");

		return ResponseEntity.ok().headers(headers)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(new InputStreamResource(file));
	}

	@PostMapping(value = "/import", consumes = "multipart/form-data")

	public ResponseEntity<String> importExcel(@RequestPart("file") MultipartFile file) throws Exception {

		String response = service.importStudentsFromExcel(file);

		return ResponseEntity.ok(response);
	}
	

	@GetMapping("/test")
	public String testApi() {
	    return "Debug Branch Working";
	}
	@GetMapping("/demo2")
	public String demo2() {
		return "hello";
	}
}

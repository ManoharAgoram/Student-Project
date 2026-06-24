package com.student.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.management.dto.CollegeDTO;
import com.student.management.entity.College;
import com.student.management.entity.Student;
import com.student.management.service.CollegeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {

	private CollegeService service;

	public CollegeController(CollegeService service) {
		this.service = service;
	}

	@PostMapping("/createColleges")
	public ResponseEntity<String> createColleges(@RequestBody @Valid List<CollegeDTO> dto) {
		String response = service.createCollege(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{collegeCode}")
	public ResponseEntity<College> getCollegeById(@PathVariable String collegeCode) {

		College college = service.getCollegeById(collegeCode);

		return ResponseEntity.status(HttpStatus.OK).body(college);
	}

	@PutMapping("/{collegeCode}")

	public ResponseEntity<String> updateCollege(@PathVariable String collegeCode, @RequestBody @Valid CollegeDTO dto) {
		String response = service.updateCollege(collegeCode, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")

	public ResponseEntity<String> deleteCollege(@PathVariable Long id) {
		String response = service.deleteCollege(id);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{collegeId}/students")

	public ResponseEntity<List<Student>> getStudentsByCollegeId(@PathVariable Long collegeId) {

		return ResponseEntity.ok(service.getStudentByCollegeId(collegeId));

	}

}

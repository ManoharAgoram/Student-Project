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

import com.student.management.dto.StudentAcademicDTO;
import com.student.management.dto.TopStudentDTO;
import com.student.management.entity.StudentAcademic;
import com.student.management.service.StudentAcademicService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("api/studentAcademic")
public class StudentAcademicController {

	private final StudentAcademicService service;

	public StudentAcademicController(StudentAcademicService service) {
		this.service = service;
	}

	@PostMapping("/addAcademic")
	public ResponseEntity<String> addAcademic(@RequestBody @Valid List<StudentAcademicDTO> dto) {

		String response = service.createAcademic(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/getAcademi/{studentId}")

	public ResponseEntity<StudentAcademic> getAcademic(@PathVariable String studentId) {

		StudentAcademic academic = service.getAcademic(studentId);

		return ResponseEntity.status(HttpStatus.CREATED).body(academic);
	}

	@PutMapping("/updateAcademic/{studentId}")

	public ResponseEntity<String> updateAcademic(@PathVariable String studentId, @RequestBody @Valid StudentAcademicDTO dto) {
		String response = service.updateAcademic(studentId, dto);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@DeleteMapping("/deleteStudent/{studentId}")

	public ResponseEntity<String> deleteStudent(@PathVariable String studentId) {

		String response = service.deleteAcademic(studentId);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/topRanker")
	public ResponseEntity<List<TopStudentDTO>> getTopCgpaStudents() {

		List<TopStudentDTO> response = service.getTopCgpaStudent();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}

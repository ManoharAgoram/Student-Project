package com.student.management.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentAcademicDTO {

	@NotNull(message = "CGPA is required")
	@DecimalMin(value = "0.0", message = "CGPA cannot be less than 0")
	@DecimalMax(value = "10.0", message = "CGPA cannot be greater than 10")
	private Double cgpa;

	@NotNull(message = "Arrears Count is required")
	private Integer arrearsCount;

	@NotNull(message = "Arrears Cleared is required")
	private Boolean arrearsCleared;

	@NotBlank(message = "Student Id is required")
	private String studentId;
}
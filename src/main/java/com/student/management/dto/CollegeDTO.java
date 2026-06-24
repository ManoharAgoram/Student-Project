package com.student.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CollegeDTO {

	@NotBlank(message = "College Name is required")
	@Size(min = 3, max = 100, message = "College Name must be between 3 and 100 characters")
	private String collegeName;

	@NotBlank(message = "College Code is required")
	@Size(min = 3, max = 20, message = "College Code must be between 3 and 20 characters")
	private String collegeCode;

	@NotBlank(message = "University Name is required")
	@Size(min = 3, max = 100, message = "University Name must be between 3 and 100 characters")
	private String universityName;
}
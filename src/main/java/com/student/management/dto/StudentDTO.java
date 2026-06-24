package com.student.management.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class StudentDTO {

	@NotBlank(message = "Student Id is required")
	private String studentId;

	@NotBlank(message = "First Name is required")
	private String firstName;

	@NotBlank(message = "Last Name is required")
	private String lastName;

	@Email(message = "Invalid Email Format")
	@NotBlank(message = "Email is required")
	private String email;

	@Past(message = "Date Of Birth must be a past date")
	@NotNull(message = "Date Of Birth is required")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Department is required")
	private String department;

	@NotNull(message = "Joined Year is required")
	private LocalDate joinedYear;

	@NotNull(message = "Passed Out Year is required")
	private LocalDate passedOutYear;

	@NotNull(message = "College Id is required")
	private Long collegeId;
}
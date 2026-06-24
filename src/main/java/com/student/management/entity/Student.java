package com.student.management.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_no")
	private Long sno;

	@Column(name = "student_id")
	private String studentId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	private String department;

	@Column(name = "joined_year")
	private LocalDate joinedYear;

	@Column(name = "passed_out_year")
	private LocalDate passedOutYear;

	@ManyToOne
	@JoinColumn(name = "college_id")
	@JsonBackReference
	private College college;
}
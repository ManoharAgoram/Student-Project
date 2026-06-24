package com.student.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_student_academic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAcademic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "academic_id")
	private Long academicId;

	private Double cgpa;

	@Column(name = "arrears_count")
	private Integer arrearsCount;

	@Column(name = "arrears_cleared")
	private Boolean arrearsCleared;

	@OneToOne
	@JoinColumn(name = "s_no", referencedColumnName = "s_no")
	private Student student;
}

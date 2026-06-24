package com.student.management.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_college")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "college_id")
	private Long collegeId;

	@Column(name = "college_name")
	private String collegeName;

	@Column(name = "college_code")
	private String collegeCode;

	@Column(name = "university_name")
	private String universityName;
	
	 @OneToMany(mappedBy = "college")
	    private List<Student> students;
}
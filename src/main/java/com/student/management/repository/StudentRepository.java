package com.student.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.management.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByStudentId(String studentId);

	void deleteByStudentId(String studentId);

	List<Student> findByCollegeCollegeId(long collegeId);

}

package com.student.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.management.entity.StudentAcademic;

@Repository
public interface StudentAcademicRepository extends JpaRepository<StudentAcademic, Long> {
	
	StudentAcademic findByStudentStudentId(String studentId);
	
	List<StudentAcademic> findAllByOrderByCgpaDesc();
}

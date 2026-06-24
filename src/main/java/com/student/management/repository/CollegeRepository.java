package com.student.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.management.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

	College findByCollegeName(String collegeName);

	College findByCollegeCode(String collegeCode);

	College findByCollegeId(long collegeId);

}

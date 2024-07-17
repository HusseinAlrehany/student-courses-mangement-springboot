package com.luv2code.studentcoursesinstructormanager.repository;

import com.luv2code.studentcoursesinstructormanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //to find student by email to check if it's already registered
    Student findByEmail(String email);
}

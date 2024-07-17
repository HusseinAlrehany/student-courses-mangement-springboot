package com.luv2code.studentcoursesinstructormanager.repository;

import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    Instructor findByEmail(String email);
}

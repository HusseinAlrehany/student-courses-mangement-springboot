package com.luv2code.studentcoursesinstructormanager.repository;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}

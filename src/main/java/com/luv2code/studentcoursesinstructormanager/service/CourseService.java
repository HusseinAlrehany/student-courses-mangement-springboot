package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.entity.Reviews;
import com.luv2code.studentcoursesinstructormanager.entity.Student;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course save(Course theCourse);

    void addStudentToCourse(int courseId, Student theStudent);

    Course findById(int courseId);

    void deleteById(int courseId);

    void deleteAll();

    void addReviewToCourse(int courseId, Reviews theReview);

    public void addCreatedReviewToCourse(int reviewId, Course theCourse);

    }

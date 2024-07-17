package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Student;

import java.util.List;

public interface StudentService {
    Student save(Student theStudent);

    List<Student> findAll();

    Student findById(int studentId);

    void addCourseToStudent(int studentId, Course theCourse);

    void deleteById(int studentId);

    void deleteAll();

    Student updateSave(Student theStudent);
}

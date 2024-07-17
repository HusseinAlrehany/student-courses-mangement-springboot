package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Instructor;

public interface InstructorService {
    Instructor save(Instructor theInstructor);

    Instructor findById(int instructorId);

    void deleteById(int instructorId);

    void addCourseToInstructor(int instructorId, Course theCourse);

    void addExistingCourseToInstructor(int instructorId, Course theCourse);

    Instructor updateInstructor(Instructor theInstructor);
}

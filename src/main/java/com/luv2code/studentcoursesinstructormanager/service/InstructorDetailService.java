package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.entity.InstructorDetail;

import java.util.List;

public interface InstructorDetailService {
    InstructorDetail save(InstructorDetail theInstructorDetail);

    List<InstructorDetail> findAll();

    InstructorDetail findById(int instructorDetailId);

    void deleteById(int instructorDetailId);

    void addInstructorToInstructorDetail(int instructorDetailId, Instructor theInstructor);
}

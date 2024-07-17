package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.exceptionhandling.NotFoundException;
import com.luv2code.studentcoursesinstructormanager.repository.CourseRepository;
import com.luv2code.studentcoursesinstructormanager.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository){
        this.instructorRepository=instructorRepository;

    }
    @Override
    public Instructor save(Instructor theInstructor) {

        Instructor dbInstructor=instructorRepository.findByEmail(theInstructor.getEmail());
        if(dbInstructor!=null){
            throw new NotFoundException("This Email Already Registered " + theInstructor.getEmail());
        }
        Instructor instructor=instructorRepository.save(theInstructor);
        return instructor;
    }

    @Override
    public Instructor findById(int instructorId) {
        Optional<Instructor> result=instructorRepository.findById(instructorId);
        Instructor dbInstructor=null;
        if(result.isEmpty()){
            throw new NotFoundException("Instructor Not Found! ID: " + instructorId);
        }
        else {
            dbInstructor=result.get();
        }
        return dbInstructor;

    }

    @Override
    public void deleteById(int instructorId) {
        Optional<Instructor> result=instructorRepository.findById(instructorId);
        if(result.isEmpty()){
            throw new NotFoundException("Instructor Not Found ID: " + instructorId);
        }
        Instructor instructor=result.get();
        List<Course> courses=instructor.getCourses();
        for(Course course:courses){
            course.setInstructors(null);
        }
        instructorRepository.deleteById(instructorId);
    }

    @Override
    public void addCourseToInstructor(int instructorId, Course theCourse) {

        Optional<Instructor> result=instructorRepository.findById(instructorId);
        Instructor dbInstructor=null;

            if(result.isPresent()){
                dbInstructor=result.get();
                dbInstructor.addCourse(theCourse);
            }
            else{
                throw new NotFoundException("Instructor Not Found " + instructorId);

            }

        }

    //add existing course to instructor
    @Override
    public void addExistingCourseToInstructor(int instructorId, Course theCourse) {

        Optional<Instructor> result=instructorRepository.findById(instructorId);
        Optional<Course> result2=courseRepository.findById(theCourse.getId());
        Instructor dbInstructor=null;
        Course dbCourse=null;
        if(result.isPresent()&& result2.isPresent()){
            dbInstructor=result.get();
            dbCourse=result2.get();
            dbInstructor.addCourse(dbCourse);
        }
        else{
            throw new NotFoundException("OOPS! Instructor Or Course  Not Found Id " + instructorId);
        }

    }

    @Override
    public Instructor updateInstructor(Instructor theInstructor) {

             Optional<Instructor> result=instructorRepository.findById(theInstructor.getId());
             if(result.isEmpty()){
                 throw new NotFoundException("Instructor is Not Found ID : " + theInstructor.getId());
             }
             Instructor dbInstructor=instructorRepository.save(theInstructor);
        return dbInstructor;
    }


}


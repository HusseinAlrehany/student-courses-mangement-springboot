package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.entity.Reviews;
import com.luv2code.studentcoursesinstructormanager.entity.Student;
import com.luv2code.studentcoursesinstructormanager.exceptionhandling.NotFoundException;
import com.luv2code.studentcoursesinstructormanager.repository.CourseRepository;
import com.luv2code.studentcoursesinstructormanager.repository.ReviewsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    private ReviewsRepository reviewsRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,ReviewsRepository reviewsRepository){
        this.courseRepository=courseRepository;
        this.reviewsRepository=reviewsRepository;
    }


    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(Course theCourse) {
        return courseRepository.save(theCourse);
    }

    @Override
    public void addStudentToCourse(int courseId, Student theStudent) {
        //find the course by id
        Optional<Course> result=courseRepository.findById(courseId);
        Course dbCourse=null;
        if(result.isPresent()){
            dbCourse=result.get();
            dbCourse.getStudents().add(theStudent);
        }
        else{
            throw new RuntimeException("Course Not Found Id " + courseId);
        }
    }

    @Override
    public Course findById(int courseId) {

        Optional<Course> result=courseRepository.findById(courseId);
        Course dbCourse=null;
        if(result.isPresent()){
            dbCourse=result.get();
        }
        else{
            throw new NotFoundException("Course Not Found ID " + courseId);
        }
        return dbCourse;
    }

    @Override
    public void deleteById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();
    }

    //ADD NEW REVIEW TO A COURSE
    @Override
    public void addReviewToCourse(int courseId, Reviews theReview) {
        //find the course by id
        Optional<Course> result=courseRepository.findById(courseId);
        Course dbCourse=null;
        if(result.isPresent()){
            dbCourse=result.get();
            dbCourse.addReviewToCourse(theReview);
        }
        else{
            throw new NotFoundException("Course Id Not Found ID " + courseId);
        }
    }

    //add created review to a course
    @Override
    public void addCreatedReviewToCourse(int reviewId, Course theCourse){
        Optional<Reviews> result=reviewsRepository.findById(reviewId);
        Optional<Course> result2=courseRepository.findById(theCourse.getId());
         Course dbCourse=null;
        if(result.isEmpty()){
            throw new NotFoundException("Review Not Found Id " + reviewId);
        } else if (result2.isEmpty()) {
            throw new NotFoundException("Course Not Found Id " + theCourse.getId());
        }
        else{
            dbCourse=result2.get();
            dbCourse.addReviewToCourse(result.get());
        }


    }

}

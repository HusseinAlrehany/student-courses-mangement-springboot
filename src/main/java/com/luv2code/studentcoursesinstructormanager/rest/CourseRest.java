package com.luv2code.studentcoursesinstructormanager.rest;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.entity.Reviews;
import com.luv2code.studentcoursesinstructormanager.entity.Student;
import com.luv2code.studentcoursesinstructormanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseRest {
    private CourseService courseService;

    @Autowired
    public CourseRest(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course theCourse) {

        //in case a user enters an id in JSON ...set it to 0
        theCourse.setId(0);
        Course dbCourse = courseService.save(theCourse);
        return dbCourse;
    }

    //ADDING STUDENT TO COURSE BY COURSE ID
    @PostMapping("/courses/{courseId}")
    public String addStudentToCourse(@PathVariable int courseId,
                                     @RequestBody Student theStudent) {
        courseService.addStudentToCourse(courseId, theStudent);
        return "Student Added to Course Id " + courseId;
    }

    //ADDING NEW REVIEW TO COURSE BY COURSE ID
    @PostMapping("/coursesreviews/{courseId}")
    public String addReviewToCourse(@PathVariable int courseId,
                                    @RequestBody Reviews theReview) {

        courseService.addReviewToCourse(courseId, theReview);

        return "Review Added To Course ID : " + courseId;
    }

    //ADD CREATED REVIEW TO COURSE
    @PostMapping("/coursesandreviews/{reviewId}")
    public String addCreatedReviewToCourse(@PathVariable int reviewId,
                                           @RequestBody Course theCourse) {

        courseService.addCreatedReviewToCourse(reviewId, theCourse);

        return "Review added to course id " + theCourse.getId();
    }
        @GetMapping("/courses/{courseId}")
        public Course findById ( @PathVariable int courseId){
            Course dbCourse = courseService.findById(courseId);

            return dbCourse;
        }
        @DeleteMapping("/courses/{courseId}")
        public String deleteById ( @PathVariable int courseId){

            courseService.deleteById(courseId);

            return "Course Deleted ID " + courseId;
        }

        @PutMapping("/courses")
        public Course update (@RequestBody Course theCourse){

            Course dbCourse = courseService.save(theCourse);
            return dbCourse;

        }

        //for deleting all the courses
        @DeleteMapping("/courses")
        public String deleteAll () {

            courseService.deleteAll();

            return "Courses Deleted Successfully";
        }


    }










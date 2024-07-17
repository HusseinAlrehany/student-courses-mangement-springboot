package com.luv2code.studentcoursesinstructormanager.rest;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InstructorRest {

    private InstructorService instructorService;

    @Autowired
    public InstructorRest (InstructorService instructorService){

        this.instructorService = instructorService;
    }



    @GetMapping("/instructors/{instructorId}")
    public Instructor findById(@PathVariable int instructorId){

        Instructor dbInstructor=instructorService.findById(instructorId);

        return dbInstructor;

    }
    @PostMapping("/instructors")
    public Instructor addInstructor(@RequestBody Instructor theInstructor){

        Instructor dbInstructor= instructorService.save(theInstructor);
        return dbInstructor;
    }

    @DeleteMapping("/instructors/{instructorId}")
    public String deleteById(@PathVariable int instructorId){

        instructorService.deleteById(instructorId);

        return "Instructor deleted Successfully Id " + instructorId;
    }

    //ADDING COURSE TO INSTRUCTOR BY INSTRUCTOR ID
    @PostMapping("/instructors/{instructorId}")
    public String addCourseToInstructor(@PathVariable int instructorId,
                                       @RequestBody Course theCourse){
        instructorService.addCourseToInstructor(instructorId,theCourse);
        return "Course Added To Instructor Id " + instructorId;
    }

    //ADDING EXISTING COURSE TO INSTRUCTOR BY INSTRUCTOR ID AND COURSE ID
    @PostMapping("/instructorsCourse/{instructorId}")
    public String addExistingCourseToinstructor(@PathVariable int instructorId,
                                                 @RequestBody Course theCourse){
        instructorService.addExistingCourseToInstructor(instructorId,theCourse);

        return "Existing Course Added to Instrcutor Id:  " + instructorId;

    }
    @PutMapping("/instructors")
    public Instructor updateInstructor(@RequestBody Instructor theInstructor){

        Instructor dbInstructor=instructorService.updateInstructor(theInstructor);

        return dbInstructor;
    }
}

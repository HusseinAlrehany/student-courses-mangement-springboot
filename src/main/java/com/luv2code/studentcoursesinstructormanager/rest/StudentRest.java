package com.luv2code.studentcoursesinstructormanager.rest;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Student;
import com.luv2code.studentcoursesinstructormanager.repository.CourseRepository;
import com.luv2code.studentcoursesinstructormanager.service.CourseService;
import com.luv2code.studentcoursesinstructormanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentRest {

    private StudentService studentService;

@Autowired
public StudentRest(StudentService studentService){
        this.studentService=studentService;
    }


    @GetMapping("/students")
    public List<Student> findAll(){

        return studentService.findAll();


    }


    @PostMapping("/students")
    public Student addStudents(@RequestBody Student theStudent){


        Student dbStudent=studentService.save(theStudent);

        return dbStudent;
    }

    //to add courses to a student using student Id
    @PostMapping("/students/{studentId}")
    public String addCourseToStudent(@PathVariable int studentId,
                                     @RequestBody Course theCourse){

      studentService.addCourseToStudent(studentId,theCourse);


    return "Course Added To Student Id " + studentId;
    }

    @GetMapping("/students/{studentId}")
    public Student findById(@PathVariable int studentId){
          Student dbStudent=studentService.findById(studentId);

          return dbStudent;
    }
    @DeleteMapping("/students/{studentId}")
    public String deleteById(@PathVariable int studentId){

        studentService.deleteById(studentId);

        return "Student Deleted Successfully ID " + studentId;
    }

    @PutMapping("/students")
    public Student updatStudent(@RequestBody Student theStudent){

          Student dbStudent=studentService.updateSave(theStudent);

          return dbStudent;
    }

    //for deleting all students
    @DeleteMapping("/students")
    public String deleteAll(){
       studentService.deleteAll();

       return "Students Deleted Successfully";
    }



}

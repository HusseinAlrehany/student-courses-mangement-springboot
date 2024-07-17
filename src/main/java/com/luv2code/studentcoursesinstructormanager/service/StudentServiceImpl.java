package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Course;
import com.luv2code.studentcoursesinstructormanager.entity.Student;
import com.luv2code.studentcoursesinstructormanager.exceptionhandling.NotFoundException;
import com.luv2code.studentcoursesinstructormanager.repository.CourseRepository;
import com.luv2code.studentcoursesinstructormanager.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){

        this.studentRepository=studentRepository;
    }


    @Override
    public Student save(Student theStudent) {

        Student dbStudent=studentRepository.findByEmail(theStudent.getEmail());
        if(dbStudent!=null){
            throw new NotFoundException("Email Is Already Registered !" + theStudent.getEmail());
        }
        return studentRepository.save(theStudent);
    }

    @Override
    public List<Student> findAll() {
        List<Student> students=studentRepository.findAll();
        if(students.isEmpty()){
            throw new NotFoundException("No Students Found");
        }

        return students;
    }

    @Override
    public Student findById(int studentId) {
        Optional<Student> result=studentRepository.findById(studentId);
        Student dbStudent=null;
        if(result.isEmpty()){
            throw new NotFoundException("Student Not Found ID : " + studentId);
        }
        else {
            dbStudent = result.get();
        }
        return dbStudent;
    }

    @Override
    public void addCourseToStudent(int studentId, Course theCourse) {
        Optional<Student> result = studentRepository.findById(studentId);
        Student dbStudent = null;
        if (result.isPresent()) {
            dbStudent = result.get();
            dbStudent.getCourses().add(theCourse);
        } else {
            throw new NotFoundException("Student Not Found Id : " + studentId);
        }
        //to add already created course to a student
        //get that student by id
        //get that course from database by id entered in JSON
        //add that course to that student
    }

    @Override
    public void deleteById(int studentId) {
          Optional<Student> result=studentRepository.findById(studentId);
          if(result.isEmpty()){
              throw new NotFoundException("Student Not Found! ID : " + studentId);
          }

        studentRepository.deleteById(studentId);

    }

    @Override
    public void deleteAll() {
        List<Student> students=studentRepository.findAll();
        if(students.isEmpty()){
            throw new NotFoundException("No Students Found To Delete!!");
        }
        studentRepository.deleteAll();
    }

    @Override
    public Student updateSave(Student theStudent) {
        Optional<Student> result=studentRepository.findById(theStudent.getId());
        if(result.isEmpty()){
            throw new NotFoundException("No Student Found ID : " + theStudent.getId());
        }

         Student dbStudent=studentRepository.save(theStudent);

        return dbStudent;
    }
}

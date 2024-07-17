package com.luv2code.studentcoursesinstructormanager.rest;

import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.entity.InstructorDetail;
import com.luv2code.studentcoursesinstructormanager.service.InstructorDetailService;
import com.luv2code.studentcoursesinstructormanager.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorDetailRest {

    private InstructorDetailService instructorDetailService;



    @Autowired
    public InstructorDetailRest(InstructorDetailService instructorDetailService){

        this.instructorDetailService=instructorDetailService;
    }



    @GetMapping("/instructorDetail")
    public List<InstructorDetail> findAll(){

        List<InstructorDetail> dbInstructorDetail=instructorDetailService.findAll();

        return dbInstructorDetail;
    }

    @GetMapping("/instructorDetail/{instructorDetailId}")
    public InstructorDetail findById(@PathVariable int instructorDetailId){
        InstructorDetail dbInstructorDetail=instructorDetailService.findById(instructorDetailId);

        return dbInstructorDetail;
    }

    //ADD INSTRUCTOR DETAIL (youtube channel and hobbies)
    @PostMapping("/instructorDetail")
    public InstructorDetail addInstructorDetail(@RequestBody InstructorDetail theInstructorDetail){

        //in case a user enters an id in json it will set it top 0
        theInstructorDetail.setId(0);

        InstructorDetail dbInstructorDetail=instructorDetailService.save(theInstructorDetail);

        return dbInstructorDetail;
    }

    @PutMapping("/instructorDetail")
    public InstructorDetail updateSave(@RequestBody InstructorDetail theInstructorDetail){

        InstructorDetail dbInstructorDetail=instructorDetailService.save(theInstructorDetail);

        return dbInstructorDetail;
    }

    @DeleteMapping("/instructorDetail/{instructorDetailId}")
    public String deleteById(@PathVariable int instructorDetailId){

        instructorDetailService.deleteById(instructorDetailId);

        return "Instructor Detail ID : (" + instructorDetailId +") successfully deleted";
    }

    //add instructor detail to an instructor
    @PostMapping("/instructorDetail/{instructorDetailId}")
    public String addInstructorToInstructorDetail(@PathVariable int instructorDetailId,
                                                  @RequestBody Instructor theInstructor){

        instructorDetailService.addInstructorToInstructorDetail(instructorDetailId,theInstructor);

        return "Instructor  Connected To Instructor Detail ID : " + instructorDetailId;

    }







}

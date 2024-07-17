package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Instructor;
import com.luv2code.studentcoursesinstructormanager.entity.InstructorDetail;
import com.luv2code.studentcoursesinstructormanager.exceptionhandling.NotFoundException;
import com.luv2code.studentcoursesinstructormanager.repository.InstructorDetailRepository;
import com.luv2code.studentcoursesinstructormanager.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstructorDetailServiceImpl implements InstructorDetailService{

    private InstructorDetailRepository instructorDetailRepository;

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    public InstructorDetailServiceImpl(InstructorDetailRepository instructorDetailRepository){

        this.instructorDetailRepository=instructorDetailRepository;

    }

    @Override
    public InstructorDetail save(InstructorDetail theInstructorDetail) {
        InstructorDetail dbInstructorDetail=instructorDetailRepository.save(theInstructorDetail);
        return dbInstructorDetail;
    }

    @Override
    public List<InstructorDetail> findAll() {
        return instructorDetailRepository.findAll();
    }

    @Override
    public InstructorDetail findById(int instructorDetailId) {
        Optional<InstructorDetail> result=instructorDetailRepository.findById(instructorDetailId);
        InstructorDetail dbInstructorDetail=null;
        if(result.isEmpty()){
            throw new NotFoundException("Instructor Detail Not Found " + instructorDetailId);
        }
        dbInstructorDetail=result.get();
        return dbInstructorDetail;
    }

    @Override
    public void deleteById(int instructorDetailId) {
        instructorDetailRepository.deleteById(instructorDetailId);
    }

    @Override
    public void addInstructorToInstructorDetail(int instructorDetailId, Instructor theInstructor) {

        Optional<InstructorDetail> result=instructorDetailRepository.findById(instructorDetailId);
        InstructorDetail dbInstructorDetail=null;
        if(result.isPresent()){
            dbInstructorDetail=result.get();
            dbInstructorDetail.addInstructorToInstructorDetail(theInstructor);
        }
        else{
            throw new NotFoundException("Instructor Detail Not Found ID " + instructorDetailId);
        }


    }


}




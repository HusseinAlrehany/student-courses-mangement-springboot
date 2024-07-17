package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Reviews;
import com.luv2code.studentcoursesinstructormanager.repository.ReviewsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewsServiceImpl implements ReviewsService{

    private ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewsRepository reviewsRepository){
        this.reviewsRepository=reviewsRepository;
    }

    @Override
    public Reviews save(Reviews theReview) {
        return reviewsRepository.save(theReview);
    }

    @Override
    public List<Reviews> findAll() {
        return reviewsRepository.findAll();
    }

    @Override
    public void deleteReview(int reviewId) {

        reviewsRepository.deleteById(reviewId);

    }
}

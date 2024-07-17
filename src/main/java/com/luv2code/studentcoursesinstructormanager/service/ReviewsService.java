package com.luv2code.studentcoursesinstructormanager.service;

import com.luv2code.studentcoursesinstructormanager.entity.Reviews;

import java.util.List;

public interface ReviewsService {
    Reviews save(Reviews theReview);

    List<Reviews> findAll();

    void deleteReview(int reviewId);
}

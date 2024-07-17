package com.luv2code.studentcoursesinstructormanager.rest;

import com.luv2code.studentcoursesinstructormanager.entity.Reviews;
import com.luv2code.studentcoursesinstructormanager.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewsRest {
    private ReviewsService reviewsService;

    @Autowired
    public ReviewsRest(ReviewsService reviewsService){
        this.reviewsService=reviewsService;
    }

    @GetMapping("/reviews")
    public List<Reviews> findAll(){
        List<Reviews> reviews=reviewsService.findAll();

        return reviews;
    }

    @PostMapping("/reviews")
    public Reviews addReview(@RequestBody Reviews theReview){
        //setting the id to 0 in case the user enters an id in the JSON
        theReview.setId(0);
        Reviews dbReview=reviewsService.save(theReview);

        return dbReview;
    }

    @PutMapping("/reviews")
    public Reviews updateReview(@RequestBody Reviews theReview){
      Reviews dbReview= reviewsService.save(theReview);

      return dbReview;
    }

    @DeleteMapping("/reviews/{reviewId}")
    public String deleteReview(@PathVariable int reviewId){

        reviewsService.deleteReview(reviewId);
        return"Review Deleted Successfully ID : " + reviewId;
    }
}

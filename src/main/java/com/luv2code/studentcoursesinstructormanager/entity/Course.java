package com.luv2code.studentcoursesinstructormanager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;

     @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,
     CascadeType.PERSIST,CascadeType.REFRESH})
     @JoinTable(name="course_student",
     joinColumns = @JoinColumn(name="course_id"),
     inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

     //the child side (which contains the foreign key)
    //should control the association
     @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
     @JoinColumn(name="instructor_id")
     private Instructor  instructors;

     @OneToMany(mappedBy = "theCourse" ,cascade = CascadeType.ALL)
     private List<Reviews> reviews;

    public Course(){}

    public Course(String title ) {
        this.title = title;
        //this.students = students;
    }

    //for adding reviews to a course
    public void addReviewToCourse(Reviews theReview){
        if(reviews==null){
            reviews=new ArrayList<>();
        }
        reviews.add(theReview);
        theReview.setTheCourse(this);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Instructor getInstructors() {
        return instructors;
    }

    public void setInstructors(Instructor instructors) {
        this.instructors = instructors;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
}

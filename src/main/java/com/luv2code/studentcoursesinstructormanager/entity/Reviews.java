package com.luv2code.studentcoursesinstructormanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="comment")
    private  String comment;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="course_id")
    private Course theCourse;


    public Reviews(){

    }

    public Reviews(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Course getTheCourse() {
        return theCourse;
    }

    public void setTheCourse(Course theCourse) {
        this.theCourse = theCourse;
    }
}

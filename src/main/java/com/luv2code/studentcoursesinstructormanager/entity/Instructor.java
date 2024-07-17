package com.luv2code.studentcoursesinstructormanager.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;

    //mapped by tells hibernate that the other end of the association
    //@ManyToOne side is responsible for managing this relationship
    //meaning that hibernate will propagate the association state transition
    //from the child side only while ignoring the parent side
    @OneToMany(mappedBy="instructors",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Course> courses;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetails;

    public Instructor(){}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    //adding the course to the list
    //setting the instructor in the course to this current instructor
    //associate method to add the course to that current instance of instructor

    public void addCourse(Course theCourse){
        if(courses==null){
            courses=new ArrayList<>();

        }
        courses.add(theCourse);

        theCourse.setInstructors(this);

    }
    //breaking the association
    public void removeCourse(Course theCourse){
        courses.remove(theCourse);
        theCourse.setInstructors(null);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public InstructorDetail getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetail instructorDetails) {
        this.instructorDetails = instructorDetails;
    }
}

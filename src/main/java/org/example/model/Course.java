package org.example.model;


import java.util.List;


public class Course {
    private String courseId;
    private String courseTitle;
    private String courseInstructor;
    private String courseStartDate;
    private String courseOfferingId;
    private int courseMaxRegistrations;
    private int courseMinRegistrations;
    private List<User> courseUsers;


    public Course(String courseId, String courseTitle, String courseInstructor, String courseStartDate, String courseOfferingId,
                  int courseMaxRegistrations, int courseMinRegistrations, List<User> courseUsers) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
        this.courseStartDate = courseStartDate;
        this.courseOfferingId = courseOfferingId;
        this.courseMaxRegistrations = courseMaxRegistrations;
        this.courseMinRegistrations = courseMinRegistrations;
        this.courseUsers = courseUsers;
    }





    public String getCourseTitle() {
        return courseTitle;
    }



    public String getCourseInstructor() {
        return courseInstructor;
    }



    public String getCourseStartDate() {
        return courseStartDate;
    }



    public String getCourseOfferingId() {
        return courseOfferingId;
    }



    public int getCourseMaxRegistrations() {
        return courseMaxRegistrations;
    }


    public int getCourseMinRegistrations() {
        return courseMinRegistrations;
    }


    public List<User> getCourseUsers() {
        return courseUsers;
    }

    public void setCourseUsers(List<User> courseUsers) {
        this.courseUsers = courseUsers;
    }


}

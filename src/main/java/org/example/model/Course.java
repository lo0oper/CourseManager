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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(String courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public int getCourseMaxRegistrations() {
        return courseMaxRegistrations;
    }

    public void setCourseMaxRegistrations(int courseMaxRegistrations) {
        this.courseMaxRegistrations = courseMaxRegistrations;
    }

    public int getCourseMinRegistrations() {
        return courseMinRegistrations;
    }

    public void setCourseMinRegistrations(int courseMinRegistrations) {
        this.courseMinRegistrations = courseMinRegistrations;
    }

    public List<User> getCourseUsers() {
        return courseUsers;
    }

    public void setCourseUsers(List<User> courseUsers) {
        this.courseUsers = courseUsers;
    }


}

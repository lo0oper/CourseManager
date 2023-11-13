package org.example.model;

public class Registration {
    private String registrationId;
    private User user;
    private Course course;
    private RegistrationsStatus registrationsStatus;

    public Registration(String registrationId, User user, Course course, RegistrationsStatus registrationsStatus) {
        this.registrationId = registrationId;
        this.user = user;
        this.course = course;
        this.registrationsStatus = registrationsStatus;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public RegistrationsStatus getRegistrationsStatus() {
        return registrationsStatus;
    }

    public void setRegistrationsStatus(RegistrationsStatus registrationsStatus) {
        this.registrationsStatus = registrationsStatus;
    }
}

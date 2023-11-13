package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Registration {
    private  String registrationId;
    private  User user;
    private  Course course;
    private  RegistrationsStatus registrationsStatus;

    public Registration(String registrationId, User user, Course course, RegistrationsStatus registrationsStatus) {
        this.registrationId = registrationId;
        this.user = user;
        this.course = course;
        this.registrationsStatus = registrationsStatus;
    }
}

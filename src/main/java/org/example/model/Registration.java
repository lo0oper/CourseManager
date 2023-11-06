package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Registration {
    private String registrationId;
    private User user;
    private Course course;
    private RegistrationsStatus registrationsStatus;
}

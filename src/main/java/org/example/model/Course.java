package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Course {
    private String courseId;
    private String courseTitle;
    private String courseInstructor;
    private String courseStartDate;
    private String courseOfferingId;
    private int courseMaxRegistrations;
    private int courseMinRegistrations;
    private List<User> courseUsers;

}

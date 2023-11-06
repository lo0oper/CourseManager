package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private String userID;
    private String email;
    private String Name;
    private List<Course> userCourse;
}

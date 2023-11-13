package org.example.model;

public class User {
    private String userID;
    private String email;
    private String name;

    public User(String userID, String email, String name) {
        this.userID = userID;
        this.email = email;
        this.name = name;
    }



    public String getEmail() {
        return email;
    }



    public String getName() {
        return name;
    }


}

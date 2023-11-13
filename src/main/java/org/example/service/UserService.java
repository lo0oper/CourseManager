package org.example.service;


import org.example.model.RegistrationsStatus;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;

import java.util.HashMap;
import java.util.List;


public class UserService {

    public String registerUser(String InputData, List<User> userList, HashMap<String,Course>courseIdMap, List<Registration> registrationsList){
        String[] inputDataParts = InputData.split(" ");
        String userEmail = inputDataParts[0];
        Course userCourse = courseIdMap.get(inputDataParts[1]);
        List<User> userWithCourse = userCourse.getCourseUsers();


        String registrationId = "REG-COURSE-"+ userEmail.split("@")[0]+"-" +userCourse.getCourseTitle();



        if(userCourse.getCourseMaxRegistrations()==userWithCourse.size()){
            return "COURSE_FULL_ERROR";
        }else{
            User newUser = new User(String.valueOf(userList.size()+1),userEmail,userEmail.split("@")[0]);

            //Registering user to registerList
            registrationsList.add(new Registration(registrationId,newUser,userCourse, RegistrationsStatus.ACCEPTED));

            //adding newUser to courseUsers list
            userWithCourse.add(newUser);

            //adding user to userList
            userList.add(newUser);
            return registrationId+" ACCEPTED";
        }

    }
    public boolean validateRegisterUserInput(String inputData){
        try{
            String[] inputDataParts = inputData.split(" ");
            String userEmail = inputDataParts[0];
            String courseOfferingId = inputDataParts[1];
            return true;
        }catch (Exception e){
            return false;
        }

    }
}

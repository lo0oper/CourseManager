package org.example.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.model.RegistrationsStatus.ACCEPTED;

@AllArgsConstructor
@Getter
@Setter
public class UserService {

    //REGISTER	 <email-id> <course-offering-id>


    public String registerUser(String InputData, List<User> userList, HashMap<String,Course>courseIdMap, List<Registration> registrationsList){
        String[] inputDataParts = InputData.split(" ");

        String userEmail = inputDataParts[0];
        String userName = userEmail.split("@")[0];
        String courseOfferingId = inputDataParts[1];
        String userId = String.valueOf(userList.size()+1);
        Course userCourse = courseIdMap.get(courseOfferingId);
        List<User> userWithCourse = userCourse.getCourseUsers();

        //creating usersCourselist and adding current course to it
        List<Course> userCourseList = new ArrayList<>();

        String registrationId = "REG-COURSE-"+ userName+"-" +userCourse.getCourseTitle();



        if(userCourse.getCourseMaxRegistrations()==userWithCourse.size()){
            return "COURSE_FULL_ERROR";
        }else{
            User newUser = new User(userId,userEmail,userName,userCourseList);

            //Registering user to registerList
            Registration registration = new Registration(registrationId,newUser,userCourse,ACCEPTED);
            registrationsList.add(registration);

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

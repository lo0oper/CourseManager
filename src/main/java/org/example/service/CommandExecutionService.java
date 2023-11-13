package org.example.service;


import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CommandExecutionService {
    List<Course> courseList = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    HashMap<String,Course> courseIdMap = new HashMap<>();
    List<Registration> registrationsList = new ArrayList<>();


    CourseService courseService = new CourseService();
    UserService userService = new UserService();
    RegistrationService registrationService = new RegistrationService();

    UtilityService utilityService = new UtilityService();

    public String scheduleCourse(String command, String inputData){
        String result;
        try{
            switch (command) {
                case "ADD-COURSE-OFFERING":
                    if(courseService.validateAddCourseInput(inputData)){
                        result = courseService.createCourse(inputData,courseList,courseIdMap,utilityService);
                    }else{
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                case "REGISTER":
                    if(userService.validateRegisterUserInput(inputData)){

                        result = userService.registerUser(inputData,userList,courseIdMap,registrationsList);

                    }else{
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                case "ALLOT":
                    if(courseService.validateAllotCoursesInput(inputData)){
                        courseService.allotCourses(courseIdMap,registrationsList,inputData);
                        String tempAns ="";
                        // Sort the list based on registrationId
                        Collections.sort(registrationsList, new UtilityService());

                        for(Registration registration: registrationsList){
                            tempAns = tempAns + registration.getRegistrationId();
                            User registeredUser = registration.getUser();
                            Course registeredCourse = registration.getCourse();
                            tempAns = tempAns + " " + registeredUser.getEmail();
                            tempAns = tempAns + " " + registeredCourse.getCourseOfferingId();
                            tempAns = tempAns + " " + registeredCourse.getCourseTitle();
                            tempAns = tempAns + " " + registeredCourse.getCourseInstructor();
                            tempAns = tempAns + " " + registeredCourse.getCourseStartDate();
                            tempAns = tempAns + " " + registration.getRegistrationsStatus().toString();
                            tempAns = tempAns + "\n";

                        }
                        result = tempAns.trim();

                    }else{
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                case "CANCEL":
                    if(registrationService.validateRegistrationCancelInput(inputData)){
                        result = inputData + " " + registrationService.cancelCourseRegistration(inputData,registrationsList);
                    }else{
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                default:
                    result = "INPUT_DATA_ERROR";
                    break;
            }
        }catch (Exception e){
            result = "INPUT_DATA_ERROR";
        }
        return result;
    }
}

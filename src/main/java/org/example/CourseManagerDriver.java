package org.example;

import org.example.exceptions.InputDataException;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;
import org.example.service.CourseService;
import org.example.service.RegistrationService;
import org.example.service.UserService;
import org.example.service.UtilityService;

import java.util.*;

public class CourseManagerDriver {
    public static void main(String[] args) throws InputDataException {
        Scanner scanner = new Scanner(System.in);

        List<Course> courseList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        HashMap<String,Course> courseIdMap = new HashMap<>();
        List<Registration> registrationsList = new ArrayList<>();


        CourseService courseService = new CourseService();
        UserService userService = new UserService();
        RegistrationService registrationService = new RegistrationService();
        while(true){
            String inputString = scanner.nextLine();
            String[] inputStringParts = inputString.split(" ",2);
            String result;
            try{
                String command = inputStringParts[0];
                String inputData = inputStringParts[1];
                switch (command) {
                    case "ADD-COURSE-OFFERING":
                        if(courseService.validateAddCourseInput(inputData)){
                            result = courseService.createCourse(inputData,courseList,courseIdMap);
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
                            List<String> output = new ArrayList<String>();
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
                            result = tempAns;

                        }else{
                            result = "INPUT_DATA_ERROR";
                        }
                        break;
                    case "CANCEL":
                        if(registrationService.validateRegistrationCancelInput(inputData)){
                            result = registrationService.cancelCourseRegistration(inputData,registrationsList);
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
            System.out.println(result);

        }


    }
}


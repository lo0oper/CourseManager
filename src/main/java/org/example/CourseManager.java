package org.example;

import org.example.exceptions.InputDataException;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;
import org.example.service.CourseService;
import org.example.service.RegistrationService;
import org.example.service.UserService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseManager {
    private final List<Course> courseList;
    private final List<User> userList;
    private final HashMap<String, Course> courseIdMap;
    private final List<Registration> registrationsList;

    private final CourseService courseService;
    private final UserService userService;
    private final RegistrationService registrationService;

    public CourseManager() {
        courseList = new ArrayList<>();
        userList = new ArrayList<>();
        courseIdMap = new HashMap<>();
        registrationsList = new ArrayList<>();
        courseService = new CourseService();
        userService = new UserService();
        registrationService = new RegistrationService();
    }

    public String processInput(String input) {
        String[] inputStringParts = input.split(" ", 2);
        String result;

        try {
            String command = inputStringParts[0];
            String inputData = inputStringParts[1];

            switch (command) {
                case "ADD-COURSE-OFFERING":
                    if (courseService.validateAddCourseInput(inputData)) {
                        result = courseService.createCourse(inputData, courseList, courseIdMap);
                    } else {
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                case "REGISTER":
                    if (userService.validateRegisterUserInput(inputData)) {
                        result = userService.registerUser(inputData, userList, courseIdMap, registrationsList);
                    } else {
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                case "ALLOT":
                    if (courseService.validateAllotCoursesInput(inputData)) {
                        courseService.allotCourses(courseIdMap, registrationsList, inputData);
                        // Return the result, e.g., a summary of registered users
                        result = summarizeRegistrations(registrationsList);
                    } else {
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                case "CANCEL":
                    if (registrationService.validateRegistrationCancelInput(inputData)) {
                        result = registrationService.cancelCourseRegistration(inputData, registrationsList);
                    } else {
                        result = "INPUT_DATA_ERROR";
                    }
                    break;
                default:
                    result = "INPUT_DATA_ERROR";
                    break;
            }
        } catch (Exception e) {
            result = "INPUT_DATA_ERROR";
        }

        return result;
    }


    private @NotNull String summarizeRegistrations(List<Registration> registrations) {
        // Implement logic to summarize the registrations
        // and return the result as a string
        // You can customize this method as needed.
        return "Summary of registrations";
    }
}

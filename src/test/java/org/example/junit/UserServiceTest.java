package org.example.junit;

import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.model.RegistrationsStatus.ACCEPTED;
import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;
    private List<User> userList;
    private HashMap<String, Course> courseIdMap;
    private List<Registration> registrationsList;

    @Before
    public void setUp() {
        userService = new UserService();
        userList = new ArrayList<>();
        courseIdMap = new HashMap<>();
        registrationsList = new ArrayList<>();
    }

    @Test
    public void testRegisterUserAccepted() {
        // Create a course with available slots
        Course course = new Course("1","CourseTitle", "Instructor", "Date", "CourseOfferingId", 10, 5, new ArrayList<>());
        courseIdMap.put("CourseOfferingId", course);

        String inputData = "user@example.com CourseOfferingId";
        String result = userService.registerUser(inputData, userList, courseIdMap, registrationsList);

        // Check that the user is registered, a registration is created, and the result is as expected
        assertTrue(result.endsWith("ACCEPTED"));
        assertFalse(result.contains("COURSE_FULL_ERROR"));
        assertEquals(1, userList.size());
        assertEquals(1, registrationsList.size());
        assertEquals(1, course.getCourseUsers().size());
    }

    @Test
    public void testRegisterUserCourseFull() {
        // Create a course with no available slots
        Course course = new Course("1","CourseTitle", "Instructor", "Date", "CourseOfferingId", 1, 1, new ArrayList<>());
        courseIdMap.put("CourseOfferingId", course);

        // Add a user to occupy the only slot in the course
        User user = new User("1", "user@example.com", "UserName");
        userList.add(user);
        course.getCourseUsers().add(user);

        String inputData = "newuser@example.com CourseOfferingId";
        String result = userService.registerUser(inputData, userList, courseIdMap, registrationsList);

        // Check that the user is not registered, and the result is "COURSE_FULL_ERROR"
        assertTrue(result.contains("COURSE_FULL_ERROR"));
        assertFalse(result.endsWith("ACCEPTED"));
        assertEquals(1, userList.size());
        assertEquals(0, registrationsList.size());
        assertEquals(1, course.getCourseUsers().size()); // User from the initial setup remains
    }

    @Test
    public void testValidateRegisterUserInputValid() {
        String inputData = "user@example.com CourseOfferingId";
        assertTrue(userService.validateRegisterUserInput(inputData));
    }

    @Test
    public void testValidateRegisterUserInputInvalid() {
        // Invalid input should return false
        String invalidInput = "InvalidInput";
        assertFalse(userService.validateRegisterUserInput(invalidInput));
    }
}

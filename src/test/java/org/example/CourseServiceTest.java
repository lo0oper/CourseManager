package org.example;

import org.example.model.RegistrationsStatus;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;
import org.example.service.CourseService;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class CourseServiceTest {
    private CourseService courseService;
    private List<Course> courseList;
    private HashMap<String, Course> courseIdMap;

    @BeforeEach
    public void setUp() {
        courseService = new CourseService();
        courseList = new ArrayList<>();
        courseIdMap = new HashMap<>();
    }

    @Test
    public void testCreateCourse() {
        String inputData = "TestCourse TestInstructor 01012023 5 10";
        String courseOfferingId = courseService.createCourse(inputData, courseList, courseIdMap);

        // Check that the course was created and added to the list and map
        assertNotNull(courseOfferingId);
        assertEquals(1, courseList.size());
        assertEquals(1, courseIdMap.size());

        // Check that the course exists in the map
        Course createdCourse = courseIdMap.get(courseOfferingId);
        assertNotNull(createdCourse);
        assertEquals("TestCourse", createdCourse.getCourseTitle());
        assertEquals("TestInstructor", createdCourse.getCourseInstructor());
        assertEquals("01012023", createdCourse.getCourseStartDate());
        assertEquals(5, createdCourse.getCourseMinRegistrations());
        assertEquals(10, createdCourse.getCourseMaxRegistrations());
    }

    @Test
    public void testCreateCourseWithExistingCourse() {
        // Add a course with the same title and instructor
        Course existingCourse = new Course("1", "TestCourse", "TestInstructor", "01012023", "OFFERING-TestCourse-TestInstructor", 5, 10, new ArrayList<>());
        courseList.add(existingCourse);
        courseIdMap.put("OFFERING-TestCourse-TestInstructor", existingCourse);

        String inputData = "TestCourse TestInstructor 01012023 5 10";
        String courseOfferingId = courseService.createCourse(inputData, courseList, courseIdMap);

        // The existing course should be returned, and nothing should be added
        assertEquals("OFFERING-TestCourse-TestInstructor", courseOfferingId);
        assertTrue(courseList.size() == 1);
        assertTrue(courseIdMap.size() == 1);
    }

    @Test
    public void testValidateAddCourseInputValid() {
        String inputData = "TestCourse TestInstructor 01012023 5 10";
        assertTrue(courseService.validateAddCourseInput(inputData));
    }

    @Test
    public void testValidateAddCourseInputInvalid() {
        // Invalid input should return false
        String inputData = "InvalidInput";
        assertFalse(courseService.validateAddCourseInput(inputData));
    }

    @Test
    public void testValidateAllotCoursesInputValid() {
        // A valid course offering ID should return true
        String courseId = "OFFERING-TestCourse-TestInstructor";
        assertTrue(courseService.validateAllotCoursesInput(courseId));
    }


    @Test
    public void testAllotCourses() {
        // Create a course and add it to the list and map
        String inputData = "JAVA JAMES 15062022 1 2";
        UserService ss ;
        List<Course> courseList = new ArrayList<>();
        HashMap<String,Course> courseIdMap = new HashMap<>();
        String courseOfferingId = courseService.createCourse(inputData, courseList, courseIdMap);

        // Create a registration for the course
        Course course = courseIdMap.get(courseOfferingId);


        List<Registration> registrationsList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        User user = new User("1","TEST@GMAIL.COM","TEST");
        userList.add(user);
        course.setCourseUsers(userList);
        Registration registration = new Registration("1",user, course, RegistrationsStatus.ACCEPTED);
        registrationsList.add(registration);


        // Allot the course, which should change the registration status to CONFIRMED
        courseService.allotCourses(courseIdMap, registrationsList, courseOfferingId);
        assertEquals(RegistrationsStatus.CONFIRMED, registration.getRegistrationsStatus());
    }
}

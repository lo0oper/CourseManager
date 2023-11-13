package org.example;

import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.RegistrationsStatus;
import org.example.model.User;
import org.example.service.CourseService;
import org.example.service.RegistrationService;

import static org.junit.jupiter.api.Assertions.*;

import org.example.service.UtilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class RegistrationServiceTest {
    private RegistrationService registrationService;
    private List<Registration> registrationsList;

    private CourseService courseService;

    private List<User> userList;

    private UtilityService utilityService;

    @BeforeEach
    public void setUp() {
        registrationService = new RegistrationService();
        registrationsList = new ArrayList<>();
        courseService = new CourseService();
        userList = new ArrayList<>();
        utilityService = new UtilityService();
    }

    @Test
    public void testSortRegistrationIdsAsc() {
        // Create and add unsorted registration IDs to the list
        String inputData = "JAVA JAMES 15062022 1 2";
        List<Course> courseList = new ArrayList<>();
        HashMap<String,Course> courseIdMap = new HashMap<>();
        String courseOfferingId = courseService.createCourse(inputData, courseList, courseIdMap,utilityService);

        Registration registration1 = new Registration("TESTID1",new User("1","TEST@GMAIL.COM","TEST"),courseIdMap.get(courseOfferingId), RegistrationsStatus.ACCEPTED);
        Registration registration2 = new Registration("TESTID2",new User("1","TEST@GMAIL.COM","TEST2"), courseIdMap.get(courseOfferingId), RegistrationsStatus.ACCEPTED);
        Registration registration3 = new Registration("ATEST1",new User("1","TEST@GMAIL.COM","ATEST"), courseIdMap.get(courseOfferingId), RegistrationsStatus.ACCEPTED);
        registrationsList.add(registration3);
        registrationsList.add(registration2);
        registrationsList.add(registration1);

        // Sort the registration IDs in ascending order
        registrationService.sortRegistrationIdsAsc(registrationsList);

        // Check that the registration IDs are sorted correctly
        assertEquals(RegistrationsStatus.ACCEPTED, registrationsList.get(0).getRegistrationsStatus());
        assertEquals(RegistrationsStatus.ACCEPTED, registrationsList.get(1).getRegistrationsStatus());
        assertEquals(RegistrationsStatus.ACCEPTED, registrationsList.get(2).getRegistrationsStatus());
    }

    @Test
    public void testCancelCourseRegistrationAccepted() {
        // Create a registration with ACCEPTED status
        User user = new User("1","TEST@GMAIL.COM","TEST");
        userList.add(user);
        Course course = new Course("CourseTitle", "Instructor", "Date", "CourseOfferingId", "Offering1", 5,1,userList);
        Registration registration = new Registration("REG-TEST-CourseTitle",user, course, RegistrationsStatus.ACCEPTED);
        registrationsList.add(registration);

        String registrationId = "REG-TEST-CourseTitle";

        // Attempt to cancel the registration
        String result = registrationService.cancelCourseRegistration(registrationId, registrationsList);

        // Check that the registration is successfully canceled
        assertEquals("CANCEL_ACCEPTED", result);
        assertTrue(registrationsList.isEmpty()); // The registration should be removed
        assertFalse(course.getCourseUsers().isEmpty()); // The user should be removed from the course
    }

    @Test
    public void testCancelCourseRegistrationRejected() {
        // Create a registration with REJECTED status
        User user = new User("1","TEST@GMAIL.COM","TEST");
        userList.add(user);
        Course course = new Course("1","CourseTitle", "Instructor", "Date", "CourseOfferingId", -1, 0, userList);
        Registration registration = new Registration("REG-COURSE-TEST-CourseTitle",user, course, RegistrationsStatus.CONFIRMED);
        registrationsList.add(registration);

        String registrationId = "REG-COURSE-TEST-CourseTitle";

        // Attempt to cancel the registration
        String result = registrationService.cancelCourseRegistration(registrationId, registrationsList);

        // Check that the cancellation is rejected
        assertEquals("CANCEL_REJECTED", result);
        assertTrue(registrationsList.contains(registration)); // The registration should not be removed
        assertFalse(course.getCourseUsers().isEmpty()); // The user should not be removed from the course
    }

    @Test
    public void testCancelCourseRegistrationNotFound() {
        // Attempt to cancel a non-existent registration
        String registrationId = "REGISTRATION-NonExistentUser-NonExistentCourse";

        // Attempt to cancel the registration
        String result = registrationService.cancelCourseRegistration(registrationId, registrationsList);

        // Check that the registration was not found and the result is "REGISTRATION_NOT_DONE"
        assertEquals("REGISTRATION_NOT_DONE", result);
    }

    @Test
    public void testValidateRegistrationCancelInputValid() {
        // A valid registration ID should return true
        String registrationId = "REGISTRATION-UserName-CourseTitle";
        assertTrue(registrationService.validateRegistrationCancelInput(registrationId));
    }


}

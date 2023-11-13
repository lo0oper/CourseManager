package org.example;

import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.RegistrationsStatus;
import org.example.service.UtilityService;
import org.example.model.User;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class UtilityServiceTest {
    private UtilityService utilityService;
    @BeforeEach
    public void setUp() {
        utilityService = new UtilityService();
    }

    @Test
    public void testCompareRegistrationIds() {

        List<User> userList = new ArrayList<>();

        // Create a list of Registration objects with existing registration IDs
        User user = new User("1","TEST1@GMAIL.COM","TEST1");
        userList.add(user);
        Course course = new Course("CourseTitle", "Instructor", "Date", "CourseOfferingId", "Offering1", 5,1,userList);

        Registration registration1 = new Registration("REG-TEST1-CourseTitle",new User("1","TEST1@GMAIL.COM","TEST2"),course, RegistrationsStatus.ACCEPTED);
        Registration registration2 = new Registration("REG-TEST2-CourseTitle",new User("2","TEST2@GMAIL.COM","TEST2"),course,RegistrationsStatus.ACCEPTED);
        Registration registration3 = new Registration("REG-TEST3-CourseTitle",new User("3","TEST3@GMAIL.COM","TEST2"),course,RegistrationsStatus.ACCEPTED);



        List<Registration> registrationList = new ArrayList<Registration>(3);
        registrationList.add(registration3);
        registrationList.add(registration1);
        registrationList.add(registration2);

        // Sort the list using the comparator
        Collections.sort(registrationList, utilityService);

        // Check that the registrations are sorted in ascending order of their registration IDs

        assertEquals(0, registrationList.indexOf(registration1));
        assertEquals(1, registrationList.indexOf(registration2));
        assertEquals(2, registrationList.indexOf(registration3));
    }


    @Test
    public void testGetCommandAndArgsForCancelRegistration(){
        String commandLine = "CANCEL REG-COURSE-ANDY-JAVA";
        String[] data = utilityService.getCommandAndArgs(commandLine);
        assertEquals("CANCEL",data[0]);
        assertEquals("REG-COURSE-ANDY-JAVA",data[1]);

    }


    @Test
    public  void testReadFileSuccessFully(){
        String inputTestFilePath = "./sample_input/testinput.txt";
        List<String > lineData = utilityService.readFileData(inputTestFilePath);
        assertEquals(1,lineData.size());
    }

    @Test
    public void testGetCourseOfferingId(){
        String courseTitle = "JAVA";
        String courseInstructor = "ANKUSH";
        String courseID = utilityService.getCourseOfferingId(courseTitle,courseInstructor);
        assertEquals("OFFERING-JAVA-ANKUSH",courseID);
    }

    @Test
    public void testGetUserNameFromEmail(){
        String email ="RAM@GMAIL.COM";
        assertEquals("RAM",utilityService.getUsernameFromEmail(email));
    }

    @Test
    public void testRegistrationServiceCancellation(){
        User user = new User("1","TEST@GMAIL.COM","TEST");
        List<User> userList = new ArrayList<>();
        List<Registration> registrationsList = new ArrayList<>();
        userList.add(user);
        Course course = new Course("1","CourseTitle", "Instructor", "Date", "CourseOfferingId", -1, 0, userList);
        Registration registration = new Registration("REG-COURSE-TEST-CourseTitle",user, course, RegistrationsStatus.CONFIRMED);
        registrationsList.add(registration);

        String registrationId = "REG-COURSE-TEST-CourseTitle";
        utilityService.removeCancelledRegistrationsFromCourse(userList,"REG-COURSE-TEST-CourseTitl");
        assertEquals(registrationsList.size(),1);
    }
}

package org.example.junit;

import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.RegistrationsStatus;
import org.example.model.User;
import org.example.service.UtilityService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UtilityServiceTest {
    @Test
    public void testCompareRegistrationIds() {
        UtilityService utilityService = new UtilityService();
        List<User> userList = new ArrayList<>();

        // Create a list of Registration objects with existing registration IDs
        User user = new User("1","TEST1@GMAIL.COM","TEST1");
        userList.add(user);
        Course course = new Course("CourseTitle", "Instructor", "Date", "CourseOfferingId", "Offering1", 5,1,userList);

        Registration registration1 = new Registration("REG-TEST1-CourseTitle",new User("1","TEST1@GMAIL.COM","TEST2"),course,RegistrationsStatus.ACCEPTED);
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
}

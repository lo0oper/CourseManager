package org.example.junit;

import org.example.CourseManager;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CourseManagerTest {
    @Test
    public void testProcessInput_AddCourseOffering() {
        CourseManager courseManager = new CourseManager();
        String input = "ADD-COURSE-OFFERING CourseTitle Instructor Date 5 1";
        String expectedOutput = "OFFERING-CourseTitle-Instructor"; // Expected output based on your application logic

        String result = courseManager.processInput(input);

        assertEquals(expectedOutput, result);
    }
    @Test
    public void testWrongInput() {
        CourseManager courseManager = new CourseManager();
        String input = "ADDCOURSE-OFFERING CourseTitle Instructor Date 5 1";
        String expectedOutput = "INPUT_DATA_ERROR"; // Expected output based on your application logic

        String result = courseManager.processInput(input);

        assertEquals(expectedOutput, result);
    }

}

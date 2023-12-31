package org.example;

import org.example.service.CommandExecutionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExecutionServiceTests {
    private CommandExecutionService commandExecutionService;


    @BeforeEach
    public void setUp() {
        commandExecutionService = new CommandExecutionService();
    }

    @Test
    public void testScheduleCourse_AddCourseOffering() {
        // Your test case for "ADD-COURSE-OFFERING" command here
        String command = "ADD-COURSE-OFFERING";
        String inputData = "KUBERNETES WOODY 16062022 2 5";

        String result = commandExecutionService.scheduleCourse(command, inputData);

        // Add your assertions here to verify the expected result
        assertEquals("OFFERING-KUBERNETES-WOODY", result);
    }

    @Test
    public void testScheduleCourse_Register() {
        // Your test case for "REGISTER" command here
        String command = "ADD-COURSE-OFFERING";
        String inputData = "JAVA JAMES 15062022 1 2";
        String result = commandExecutionService.scheduleCourse(command, inputData);
        command = "REGISTER";
        inputData = "ANDY@GMAIL.COM OFFERING-JAVA-JAMES";
        result = commandExecutionService.scheduleCourse(command, inputData);

        // Add your assertions here to verify the expected result
        assertEquals("REG-COURSE-ANDY-JAVA ACCEPTED", result);
    }

    @Test
    public void testScheduleCourse_Allot() {

        String command = "ADD-COURSE-OFFERING";
        String inputData = "JAVA JAMES 15062022 1 2";
        String result = commandExecutionService.scheduleCourse(command, inputData);
        command = "REGISTER";
        inputData = "ANDY@GMAIL.COM OFFERING-JAVA-JAMES";
        result = commandExecutionService.scheduleCourse(command, inputData);

        // Your test case for "ALLOT" command here
        command = "ALLOT";
        inputData = "OFFERING-JAVA-JAMES";
        result = commandExecutionService.scheduleCourse(command, inputData);

        // Add your assertions here to verify the expected result
        assertEquals("REG-COURSE-ANDY-JAVA ANDY@GMAIL.COM OFFERING-JAVA-JAMES JAVA JAMES 15062022 CONFIRMED", result);
    }

    @Test
    public void testScheduleCourseCancelRejected() {
        String command = "ADD-COURSE-OFFERING";
        String inputData = "JAVA JAMES 15062022 1 2";
        String result;
        result = commandExecutionService.scheduleCourse(command, inputData);
        command = "REGISTER";
        inputData = "ANDY@GMAIL.COM OFFERING-JAVA-JAMES";
        result = commandExecutionService.scheduleCourse(command, inputData);

        // Your test case for "ALLOT" command here
        command = "ALLOT";
        inputData = "OFFERING-JAVA-JAMES";
        result = commandExecutionService.scheduleCourse(command, inputData);

        command = "CANCEL";
        inputData = "REG-COURSE-ANDY-JAVA";

        result = commandExecutionService.scheduleCourse(command, inputData);

        assertEquals("REG-COURSE-ANDY-JAVA CANCEL_REJECTED", result);
    }

    @Test
    public void testScheduleCourseCancelAccepted() {
        String command = "ADD-COURSE-OFFERING";
        String inputData = "JAVA JAMES 15062022 1 2";
        commandExecutionService.scheduleCourse(command, inputData);
        String result;
        command = "REGISTER";
        inputData = "ANDY@GMAIL.COM OFFERING-JAVA-JAMES";
        result = commandExecutionService.scheduleCourse(command, inputData);

        command = "CANCEL";
        inputData = "REG-COURSE-ANDY-JAVA";

        result = commandExecutionService.scheduleCourse(command, inputData);

        assertEquals("REG-COURSE-ANDY-JAVA CANCEL_ACCEPTED", result);
    }

    @Test
    public void testIncorrectInputData(){
        String result = commandExecutionService.scheduleCourse("TEST_COMMAND","TEST_INPUT");
        assertEquals("INPUT_DATA_ERROR",result);
    }
}

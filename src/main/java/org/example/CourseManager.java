
package org.example;

import org.example.exceptions.InputDataException;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;
import org.example.service.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;




public class CourseManager {
    public static void main(String[] args) throws InputDataException {
        CommandExecutionService commandExecutionService = new CommandExecutionService();
        UtilityService utilityService = new UtilityService();

        Scanner scanner = new Scanner(System.in);
        if (args.length != 1) {
            while(true){
                String inputString = scanner.nextLine();
                String[] inputStringParts = inputString.split(" ",2);
                String result = commandExecutionService.scheduleCourse(inputStringParts[0],inputStringParts[1]);
                System.out.println(result);
            }

        } else {
            List<String> InputLines = utilityService.readFileData(args[0]);
            for(String inputLine :InputLines){
                String[] data = utilityService.getCommandAndArgs(inputLine);
                String command = data[0];
                String inputData = data[1];
                String result = commandExecutionService.scheduleCourse(command,inputData);
                System.out.println(result);
            }


        }



    }
}
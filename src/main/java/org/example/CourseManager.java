
package org.example;

import org.example.exceptions.InputDataException;
import org.example.service.CommandExecutionService;
import org.example.service.UtilityService;

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
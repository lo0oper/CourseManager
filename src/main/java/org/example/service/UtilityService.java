package org.example.service;

import org.example.model.Registration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UtilityService implements Comparator<Registration> {
    @Override
    public int compare(Registration reg1, Registration reg2) {
        // Compare Registration objects based on their registrationId
        return reg1.getRegistrationId().compareTo(reg2.getRegistrationId());
    }

    public List<String> readFileData(String fileName){
        List<String> commands= new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.singletonList("NO_INPUT");
        }
        return commands;

    }

    public String[] getCommandAndArgs(String str){
        try{
            String[] inputStringParts = str.split(" ",2);
            String command = inputStringParts[0];
            String inputData = inputStringParts[1];
            return new String[]{command, inputData};
        }catch (Exception e){
            return new String[]{"INPUT_DATA_ERROR"};
        }

    }
}

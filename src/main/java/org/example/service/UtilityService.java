package org.example.service;

import org.example.model.Registration;
import org.example.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
            return new String[]{str.split(" ",2)[0], str.split(" ",2)[1]};
        }catch (Exception e){
            return new String[]{"INPUT_DATA_ERROR"};
        }

    }

    public String getUsernameFromEmail(String email){
        return email.split("@")[0];
    }

    public String getCourseOfferingId(String courseTitle, String courseInstructor){
        return "OFFERING-"+courseTitle+"-"+courseInstructor;
    }


    public boolean removeCancelledRegistrationsFromCourse(List<User> registeredUserListForCourse, String registrationId){
        for(User user:registeredUserListForCourse){
            if(user.getName().equals(registrationId.split("-")[2])){
                //removing user from course's userlist
                registeredUserListForCourse.remove(user);
                return true;
            }
        }
        return false;

    }

    public boolean removeRegistrationFromRegistrationList(List<Registration> registrationList, String registrationId){
        for(Registration registration: registrationList){
            if(registration.getRegistrationId().equals(registrationId)){
                registrationList.remove(registration);
                return true;
            }
        }
        return false;

    }
}

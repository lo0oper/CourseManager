package org.example.service;

import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.RegistrationsStatus;
import org.example.model.User;

import java.util.List;

public class RegistrationService {


    public void sortRegistrationIdsAsc(List<Registration> registrationList){

    }


    public String cancelCourseRegistration(String registrationId, List<Registration> registrationsList){
        //TODO: this can be optimised by maintaining a map of RegistrationId vs Registration model
        for(Registration registration : registrationsList){
            if(registration.getRegistrationId().equals(registrationId)){ // Registration found
                if(registration.getRegistrationsStatus()== RegistrationsStatus.ACCEPTED){

                    //removing user from course
                    String userName = registrationId.split("-")[2];
                    Course registeredCourse = registration.getCourse();
                    List<User> registeredUserList = registeredCourse.getCourseUsers();
                    for(User user:registeredUserList){
                        if(user.getName().equals(userName)){
                            //removing user from course's userlist
                            registeredUserList.remove(user);
                            break;
                        }
                    }
                    //remove registration from registrationList
                    registrationsList.remove(registration);

                    return "CANCEL_ACCEPTED";
                }else{
                    return "CANCEL_REJECTED";
                }
            }
        }

        //Adding this Corner case of registration was not done.
        return "REGISTRATION_NOT_DONE";
    }

    public boolean validateRegistrationCancelInput(String inputData){
        try{
            String courseRegistrationId = inputData;
            return true;
        }catch (Exception e){
            return false;
        }
    }



}

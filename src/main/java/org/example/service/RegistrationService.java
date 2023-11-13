package org.example.service;

import org.example.model.RegistrationsStatus;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;

import java.util.Iterator;
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
                    List<User> registeredUserList = registration.getCourse().getCourseUsers();
                    Iterator<User> iterator = registeredUserList.iterator();
                    while (iterator.hasNext()) {
                        User user = iterator.next();
                        if (user.getName().equals(registrationId.split("-")[2])) {
                            iterator.remove(); // remove the user using the iterator
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

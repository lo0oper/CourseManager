package org.example.service;


import org.example.model.Registration;
import org.example.model.RegistrationsStatus;
import org.example.model.User;


import java.util.List;

public class RegistrationService {


    public void sortRegistrationIdsAsc(List<Registration> registrationList){

    }


    public String cancelCourseRegistration(String registrationId, List<Registration> registrationsList,UtilityService utilityService){
        //TODO: this can be optimised by maintaining a map of RegistrationId vs Registration model
        for(Registration registration : registrationsList){
            if(registration.getRegistrationId().equals(registrationId)){ // Registration found
                if(registration.getRegistrationsStatus()== RegistrationsStatus.ACCEPTED){
                    //Getting the userslist from the course of registration
                    List<User> registeredUserListForCourse = registration.getCourse().getCourseUsers();
                    if(utilityService.removeCancelledRegistrationsFromCourse(registeredUserListForCourse,registrationId) &&
                            utilityService.removeRegistrationFromRegistrationList(registrationsList,registrationId) ){
                        //remove registration from registrationList
                        ;
                        return "CANCEL_ACCEPTED";
                    }
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

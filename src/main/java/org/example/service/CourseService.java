package org.example.service;


import org.example.model.RegistrationsStatus;
import org.example.model.Course;
import org.example.model.Registration;
import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseService {
    //<course-name> <instructor> <date-in-ddmmyyyy> <minEmployees> <maxEmployees>

    public String createCourse(String inputData, List<Course> courseList, HashMap<String,Course> courseIdMap, UtilityService utilityService) {
        String[] inputDataParts = inputData.split(" ");
        String courseOfferingId = utilityService.getCourseOfferingId( inputDataParts[0],inputDataParts[1]);
        // Check for registering already registered course. Since this case is not mentioned in problem statement ignoring this
        if(courseIdMap.containsKey(courseOfferingId)){
            return courseOfferingId;
        }else{
            Course newCourse = new Course(String.valueOf((courseList.size() + 1)), inputDataParts[0], inputDataParts[1], inputDataParts[2],courseOfferingId, Integer.parseInt(inputDataParts[4]), Integer.parseInt(inputDataParts[3]), new ArrayList<>());
            courseList.add(newCourse);

            // Adding course to hashmap
            courseIdMap.put(courseOfferingId,newCourse);
            return courseOfferingId;
        }
    }

    public boolean validateAddCourseInput(String inputData) {
        try{
            String[] inputDataParts = inputData.split(" ");
            String courseTitle = inputDataParts[0];
            String courseInstructor = inputDataParts[1];
            String courseDate = inputDataParts[2];
            int minEmployees = Integer.parseInt(inputDataParts[3]);
            int maxEmployees = Integer.parseInt(inputDataParts[4]);
            return true;
        }
        catch (Exception e){
            return false;
        }


    }

    public boolean validateAllotCoursesInput(String inputData){
        try{
            String courseOfferingId = inputData;
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public void allotCourses(HashMap<String,Course> courseIdMap ,List<Registration> registrationsList, String courseId){
        Course course = courseIdMap.get(courseId);
        List<User> courseUsers = course.getCourseUsers();
        RegistrationsStatus courseStatus ;

        if(courseUsers.size()<course.getCourseMinRegistrations()){
            courseStatus = RegistrationsStatus.COURSE_CANCELED;
        }else{
            courseStatus = RegistrationsStatus.CONFIRMED;
        }
        for(Registration registration: registrationsList){

            //Updating registration status to confirm for users of this course
            if(registration.getCourse().getCourseTitle().equals(course.getCourseTitle())){
                registration.setRegistrationsStatus(courseStatus);
            }
        }
    }
}

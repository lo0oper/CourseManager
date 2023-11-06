package org.example.service;

import org.example.model.Registration;

import java.util.Comparator;

public class UtilityService implements Comparator<Registration> {
        @Override
        public int compare(Registration reg1, Registration reg2) {
            // Compare Registration objects based on their registrationId
            return reg1.getRegistrationId().compareTo(reg2.getRegistrationId());
        }
}

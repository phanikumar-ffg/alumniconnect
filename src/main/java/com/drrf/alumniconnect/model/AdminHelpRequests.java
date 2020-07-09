package com.drrf.alumniconnect.model;

public class AdminHelpRequests {
    public AdminHelpRequests(UserProfile up,AdminHelpRequestStatus ahrs){
        this.userProfile=up;
        this.adminHelpRequestStatus=ahrs;
    }

    private final UserProfile userProfile;
    private final AdminHelpRequestStatus adminHelpRequestStatus;




    public UserProfile getUserProfile() {
        return userProfile;
    }

    public AdminHelpRequestStatus getAdminHelpRequestStatus() {
        return adminHelpRequestStatus;
    }
}

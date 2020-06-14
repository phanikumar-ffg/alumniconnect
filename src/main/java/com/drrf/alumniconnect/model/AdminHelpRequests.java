package com.drrf.alumniconnect.model;

public class AdminHelpRequests {
    public AdminHelpRequests(HelpHistory hh,UserProfile up,AdminHelpRequestStatus ahrs){
        this.helpHistory=hh;
        this.userProfile=up;
        this.adminHelpRequestStatus=ahrs;
    }

    private final HelpHistory helpHistory;
    private final UserProfile userProfile;
    private final AdminHelpRequestStatus adminHelpRequestStatus;

    public HelpHistory getHelpHistory() {
        return helpHistory;
    }



    public UserProfile getUserProfile() {
        return userProfile;
    }

    public AdminHelpRequestStatus getAdminHelpRequestStatus() {
        return adminHelpRequestStatus;
    }
}

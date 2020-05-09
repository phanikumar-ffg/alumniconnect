package com.drrf.alumniconnect.model;

public class AdminHelpRequests {
    private HelpHistory helpHistory;
    private UserProfile userProfile;

    public HelpHistory getHelpHistory() {
        return helpHistory;
    }

    public void setHelpHistory(HelpHistory helpHistory) {
        this.helpHistory = helpHistory;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}

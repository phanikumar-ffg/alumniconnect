package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.model.UserProfile;

public interface ProfileInformationDao {

    public UserProfile getProfileInfo(String input);
    public UserProfile updateProfileInfo(UserProfile userProfile);

}
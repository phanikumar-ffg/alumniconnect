package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.model.UserProfile;
import java.util.List;

public interface ProfileInformationService {

	public UserProfile getProfileInfo(String input) throws Exception;
	public void updateProfileInfo(UserProfile userProfile) throws Exception;
}

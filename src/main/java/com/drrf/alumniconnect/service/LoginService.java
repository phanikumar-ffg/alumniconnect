package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.UserNotFoundDaoException;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;

public interface LoginService {

	public UserProfile getUserDetails(LoginDetails user) throws UserNotFoundDaoException;

}

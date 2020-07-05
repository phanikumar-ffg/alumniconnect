package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.UserNotFoundDaoException;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;

public interface LoginDao {

	public UserProfile getUserDetails(LoginDetails user) throws UserNotFoundDaoException;

}

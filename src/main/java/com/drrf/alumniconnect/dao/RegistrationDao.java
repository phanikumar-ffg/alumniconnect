package com.drrf.alumniconnect.dao;


import com.drrf.alumniconnect.exceptions.RegistrationDaoException;
import com.drrf.alumniconnect.model.CentreDetails;
import com.drrf.alumniconnect.model.UserProfile;

import java.util.List;

public interface RegistrationDao {

	public List<CentreDetails> getCentres() throws Exception;

	public UserProfile newUserRegistration(UserProfile userProfile) throws RegistrationDaoException;

}


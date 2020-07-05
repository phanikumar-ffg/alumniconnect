package com.drrf.alumniconnect.service;

import javax.mail.AuthenticationFailedException;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.exceptions.RegistrationDaoException;
import com.drrf.alumniconnect.model.CentreDetails;
import com.drrf.alumniconnect.model.UserProfile;

import java.util.List;

public interface RegistrationService {
	public List<CentreDetails> getCentres() throws Exception;

	public UserProfile newUserRegistration(UserProfile userProfile) throws RegistrationDaoException;

}


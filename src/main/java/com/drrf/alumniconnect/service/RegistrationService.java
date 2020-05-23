package com.drrf.alumniconnect.service;

import javax.mail.AuthenticationFailedException;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.UserProfile;

public interface RegistrationService {

	public UserProfile newUserRegistration(UserProfile userProfile) throws ForgotPasswordDaoException, AuthenticationFailedException;

}


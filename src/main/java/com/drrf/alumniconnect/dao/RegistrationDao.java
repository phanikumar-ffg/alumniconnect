package com.drrf.alumniconnect.dao;

import javax.mail.AuthenticationFailedException;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.UserProfile;

public interface RegistrationDao {

	public String newUserRegistration(UserProfile userProfile) throws ForgotPasswordDaoException, AuthenticationFailedException ;

}

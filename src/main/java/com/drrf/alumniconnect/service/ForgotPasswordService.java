package com.drrf.alumniconnect.service;

import javax.mail.AuthenticationFailedException;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;

public interface ForgotPasswordService {

	public String getForgotPasswordInformation(String email) throws ForgotPasswordDaoException, AuthenticationFailedException;

}

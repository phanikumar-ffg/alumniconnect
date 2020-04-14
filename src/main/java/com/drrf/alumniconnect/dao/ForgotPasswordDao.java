package com.drrf.alumniconnect.dao;

import javax.mail.AuthenticationFailedException;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;

public interface ForgotPasswordDao {

	public String getForgotPasswordInformation(String email) throws ForgotPasswordDaoException, AuthenticationFailedException ;

}

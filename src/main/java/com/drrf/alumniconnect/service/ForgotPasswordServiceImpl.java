package com.drrf.alumniconnect.service;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drrf.alumniconnect.dao.ForgotPasswordDao;
import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	@Autowired
	ForgotPasswordDao dao;

	@Override
	public String getForgotPasswordInformation(String email) throws ForgotPasswordDaoException, AuthenticationFailedException {
		
		return dao.getForgotPasswordInformation(email);
	}

	

}

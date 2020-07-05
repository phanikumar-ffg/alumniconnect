package com.drrf.alumniconnect.service;

import javax.mail.AuthenticationFailedException;

import com.drrf.alumniconnect.exceptions.RegistrationDaoException;
import com.drrf.alumniconnect.model.CentreDetails;
import com.drrf.alumniconnect.model.JobInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drrf.alumniconnect.dao.ForgotPasswordDao;
import com.drrf.alumniconnect.dao.RegistrationDao;
import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.UserProfile;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	RegistrationDao dao;

	public List<CentreDetails> getCentres() throws Exception{
		return dao.getCentres();
	}

	@Override
	public UserProfile newUserRegistration(UserProfile userProfile) throws RegistrationDaoException {
		
		return dao.newUserRegistration(userProfile);
	}

	

}


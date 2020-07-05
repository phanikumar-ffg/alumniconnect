package com.drrf.alumniconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drrf.alumniconnect.dao.LoginDao;
import com.drrf.alumniconnect.exceptions.UserNotFoundDaoException;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;

	@Override
	public UserProfile getUserDetails(LoginDetails user) throws UserNotFoundDaoException{
		return loginDao.getUserDetails(user);
	}

}

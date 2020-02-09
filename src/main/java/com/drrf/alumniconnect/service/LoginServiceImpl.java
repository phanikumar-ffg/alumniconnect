package com.drrf.alumniconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drrf.alumniconnect.dao.LoginDao;
import com.drrf.alumniconnect.model.UserDetails;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;

	@Override
	public UserDetails getUserDetails(UserDetails user) {
			
		return loginDao.getUserDetails(user);
	}

}

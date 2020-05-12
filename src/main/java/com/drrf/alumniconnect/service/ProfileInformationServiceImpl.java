package com.drrf.alumniconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.drrf.alumniconnect.dao.JobInformationDao;
import com.drrf.alumniconnect.model.JobInformation;

@Service
public class ProfileInformationServiceImpl implements ProfileInformationService {
	@Autowired
	ProfileInformationDao ProfileInformationDao;

	@Override
	public UserProfile getProfileInfo(String input) throws Exception{
		return ProfileInformationDao.getProfileInfo(String input);
	}

	@Override
	public void updateProfileInfo(UserProfile userProfile) throws Exception{
		return ProfileInformationDao.updateProfileInfo(userProfile);
	}

	@Override
	public UserProfile saveProfileDetails(UserProfile userProfile) throws Exception{
		return ProfileInformationDao.saveProfileDetails(userProfile);
	}

}

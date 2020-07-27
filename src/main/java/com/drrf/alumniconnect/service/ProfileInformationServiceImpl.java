package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.model.CertificateRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.drrf.alumniconnect.dao.ProfileInformationDao;

import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.LoginDetails;


@Service
public class ProfileInformationServiceImpl implements ProfileInformationService {
	@Autowired
	ProfileInformationDao ProfileInformationDao;

	@Override
	public UserProfile getProfileInfo(String input) throws Exception{
		return ProfileInformationDao.getProfileInfo(input);
	}

	@Override
	public String requestCertificate(CertificateRequestObject certificateRequestObject) throws Exception{
		return ProfileInformationDao.requestCertificate(certificateRequestObject);
	}

	@Override
	public UserProfile saveProfileDetails(UserProfile userProfile) throws Exception{
		return ProfileInformationDao.saveProfileDetails(userProfile);
	}

	@Override
	public String updatePassword(LoginDetails newCredentials) throws Exception{
		return ProfileInformationDao.updatePassword(newCredentials);
	}

}

package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.model.CertificateRequestObject;
import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.LoginDetails;
import java.util.List;

public interface ProfileInformationService {

	public UserProfile getProfileInfo(String input) throws Exception;
	public String requestCertificate(CertificateRequestObject certificateRequestObject) throws Exception;
	public UserProfile saveProfileDetails(UserProfile userProfile) throws Exception;
	public String updatePassword(LoginDetails newCredentials) throws Exception;
}

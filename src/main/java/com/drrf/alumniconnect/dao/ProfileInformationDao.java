package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.model.CertificateRequestObject;
import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.exceptions.UserProfileInformationDaoException;

public interface ProfileInformationDao {

    public UserProfile getProfileInfo(String input) throws UserProfileInformationDaoException;
    public String requestCertificate(CertificateRequestObject certificateRequestObject) throws UserProfileInformationDaoException;
    public UserProfile saveProfileDetails(UserProfile userProfile) throws UserProfileInformationDaoException;
    public String updatePassword(LoginDetails newCredentials) throws UserProfileInformationDaoException;
}
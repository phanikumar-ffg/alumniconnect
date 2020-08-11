package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.UserDetailsForNotification;

public interface SaveUserTokenService {
    public String saveUserToken(String userToken) throws ContentNotFoundDaoException;
}

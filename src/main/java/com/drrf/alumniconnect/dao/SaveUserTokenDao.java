package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.UserDetailsForNotification;

public interface SaveUserTokenDao {
    public String saveUserToken(String userToken) throws ContentNotFoundDaoException;
}

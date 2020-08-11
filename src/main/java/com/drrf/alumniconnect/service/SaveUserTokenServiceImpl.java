package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.ContentRequestDao;
import com.drrf.alumniconnect.dao.SaveUserTokenDao;
import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import com.drrf.alumniconnect.model.UserDetailsForNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserTokenServiceImpl implements SaveUserTokenService {
    @Autowired
    SaveUserTokenDao UserTokenDao;

    @Override
    public String saveUserToken(String userToken) throws ContentNotFoundDaoException{
        return UserTokenDao.saveUserToken(userToken);
    }

}

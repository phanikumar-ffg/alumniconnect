package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.AdminHelpDao;
import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.util.List;

@Service
public class AdminHelpServiceImpl implements AdminHelpService {
    @Autowired
    AdminHelpDao adminhelpdao;

    @Override
    public List<AdminHelpRequests> getAllHelpRequests()  throws ForgotPasswordDaoException, AuthenticationFailedException {
        return adminhelpdao.getAllRequests();
    }
}

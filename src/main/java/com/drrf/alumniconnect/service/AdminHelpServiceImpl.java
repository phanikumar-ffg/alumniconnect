package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.AdminHelpDao;
import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.AuthenticationFailedException;
import java.util.List;
import java.util.Map;

@Service
public class AdminHelpServiceImpl implements AdminHelpService {
    @Autowired
    AdminHelpDao adminhelpdao;

    @Override
    public List<Map<String,Object>> getAllHelpRequests()  throws ForgotPasswordDaoException, AuthenticationFailedException {
        return adminhelpdao.getAllRequests();
    }

    @Override
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) throws ForgotPasswordDaoException,AuthenticationFailedException{
        return adminhelpdao.updateAdminHelpStatus(adminHelpRequestStatus);
    }
}

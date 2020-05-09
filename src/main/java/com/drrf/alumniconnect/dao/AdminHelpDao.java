package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;

import javax.mail.AuthenticationFailedException;
import java.util.List;


public interface AdminHelpDao {
    public List<AdminHelpRequests> getAllRequests() throws ForgotPasswordDaoException, AuthenticationFailedException;
}

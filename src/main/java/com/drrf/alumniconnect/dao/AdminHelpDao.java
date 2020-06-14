package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;

import javax.mail.AuthenticationFailedException;
import java.util.List;
import java.util.Map;


public interface AdminHelpDao {
    public List<Map<String,Object>> getAllRequests() throws ForgotPasswordDaoException, AuthenticationFailedException;
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) throws  ForgotPasswordDaoException,AuthenticationFailedException;

}

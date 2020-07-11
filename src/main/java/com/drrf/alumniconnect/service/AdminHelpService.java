package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.AdminHelpRequestDaoException;
import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;

import javax.mail.AuthenticationFailedException;
import java.util.List;
import java.util.Map;

public interface AdminHelpService {
    public List<Map<String, Object>> getAllHelpRequests() throws AdminHelpRequestDaoException;
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) throws AdminHelpRequestDaoException;

}

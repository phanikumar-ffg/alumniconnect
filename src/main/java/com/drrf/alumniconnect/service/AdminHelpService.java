package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequests;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.UserProfile;

import javax.mail.AuthenticationFailedException;
import java.util.List;
import java.util.Map;

public interface AdminHelpService {
      public List<AdminHelpRequests> getAllHelpRequests()  throws ForgotPasswordDaoException, AuthenticationFailedException;

}

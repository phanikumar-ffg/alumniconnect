package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.AdminHelpRequestDaoException;

import com.drrf.alumniconnect.model.AdminHelpRequestStatus;

import java.util.List;
import java.util.Map;

public interface AdminHelpService {
    public List<Map<String, Object>> getAllHelpRequests() throws AdminHelpRequestDaoException;
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) throws AdminHelpRequestDaoException;

}

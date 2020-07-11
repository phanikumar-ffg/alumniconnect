package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.AdminHelpRequestDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import java.util.List;
import java.util.Map;


public interface AdminHelpDao {
    public List<Map<String,Object>> getAllRequests() throws AdminHelpRequestDaoException;
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) throws  AdminHelpRequestDaoException;

}

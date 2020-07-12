package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.AdminHelpDao;
import com.drrf.alumniconnect.exceptions.AdminHelpRequestDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class AdminHelpServiceImpl implements AdminHelpService {
    @Autowired
    AdminHelpDao adminhelpdao;

    @Override
    public List<Map<String,Object>> getAllHelpRequests()  throws AdminHelpRequestDaoException {
        return adminhelpdao.getAllRequests();
    }

    @Override
    public String updateAdminHelpStatus(AdminHelpRequestStatus adminHelpRequestStatus) throws AdminHelpRequestDaoException{
        return adminhelpdao.updateAdminHelpStatus(adminHelpRequestStatus);
    }
}

package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.ContentManagementDao;
import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentManagementServiceImpl implements ContentManagementService {
    @Autowired
    ContentManagementDao contentManagementDao;

    @Override
    public List<ContentManagement> getAllContentInformation() throws Exception{
        return contentManagementDao.getAllContentInformation();
    }
}

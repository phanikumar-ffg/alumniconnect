package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.ContentRequestDao;
import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.beans.factory.annotation.Autowired;

public class ContentRequestServiceImpl implements ContentRequestService{
    @Autowired
    ContentRequestDao contentDao;

    @Override
    public String sendContentRequest(ContentManagement contentManagement) {
        return ContentRequestDao.sendContentRequest(contentManagement);
    }

}

package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.ContentRequestDao;
import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentRequestServiceImpl implements ContentRequestService{
    @Autowired
    ContentRequestDao contentDao;

    @Override
    public String sendContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException {
        return contentDao.sendContentRequest(contentManagement);
    }

}

package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.dao.ContentIncrementViewsDao;
import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncrementViewsServiceImpl implements IncrementViewsService{
    @Autowired
    ContentIncrementViewsDao contentIncrementViewsDao;

    @Override
    public String IncrementViewRequest(ContentManagement content) throws ContentNotFoundDaoException {
        return contentIncrementViewsDao.incrementContentViewsWithContentId(content);
    }
}

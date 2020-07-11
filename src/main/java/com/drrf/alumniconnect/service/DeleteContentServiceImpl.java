package com.drrf.alumniconnect.service;


import com.drrf.alumniconnect.dao.DeleteContentDao;
import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteContentServiceImpl implements DeleteContentService{
    @Autowired
    DeleteContentDao deleteContentDao;

    @Override
    public String deleteContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException {
         return deleteContentDao.deleteContentRequest(contentManagement);
    }
}

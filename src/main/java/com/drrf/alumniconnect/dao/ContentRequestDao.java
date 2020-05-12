package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

public interface ContentRequestDao {
    public String sendContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException;
}

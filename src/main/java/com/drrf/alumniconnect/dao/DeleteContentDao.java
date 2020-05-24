package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

public interface DeleteContentDao {
    public String deleteContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException;
}

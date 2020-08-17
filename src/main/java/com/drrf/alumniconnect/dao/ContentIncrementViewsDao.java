package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

public interface ContentIncrementViewsDao {
    public String incrementContentViewsWithContentId(ContentManagement contentManagement) throws ContentNotFoundDaoException;
}

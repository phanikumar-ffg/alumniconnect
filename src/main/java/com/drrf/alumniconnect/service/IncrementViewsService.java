package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

public interface IncrementViewsService {
    public String IncrementViewRequest(ContentManagement content) throws ContentNotFoundDaoException;
}

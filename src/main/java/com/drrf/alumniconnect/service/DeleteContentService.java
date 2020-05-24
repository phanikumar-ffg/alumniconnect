package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

public interface DeleteContentService {
    public String deleteContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException;
}

package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

public interface ContentRequestService {
    public String sendContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException;
}

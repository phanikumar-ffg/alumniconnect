package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;

import java.util.List;

public interface ContentManagementDao {
    public List<ContentManagement> getAllContentInformation() throws ContentNotFoundDaoException;
}

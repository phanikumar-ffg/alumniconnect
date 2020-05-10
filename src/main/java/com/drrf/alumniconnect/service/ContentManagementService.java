package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.model.ContentManagement;

import java.util.List;

public interface ContentManagementService {
    public List<ContentManagement> getAllContentInformation() throws Exception;
}

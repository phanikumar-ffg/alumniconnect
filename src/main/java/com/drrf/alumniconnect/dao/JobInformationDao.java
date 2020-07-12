package com.drrf.alumniconnect.dao;

import java.util.List;

import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import com.drrf.alumniconnect.model.JobInformation;

public interface JobInformationDao {

    public List<JobInformation> getJobs(Long studentId) throws Exception;
    public String saveJobEntryDetails(JobInformation jobInformation) throws JobInformationDaoException;
}
package com.drrf.alumniconnect.dao;

import java.util.List;

import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import com.drrf.alumniconnect.model.JobInformation;
import com.drrf.alumniconnect.model.JobRequest;

public interface JobInformationDao {

    public List<JobInformation> getJobs(Long studentId) throws Exception;
    public List<JobInformation> getAllJobs() throws Exception;
    public String saveJobEntryDetails(JobInformation jobInformation) throws JobInformationDaoException;
    public String deleteJobEntry(JobRequest jobInformation) throws JobInformationDaoException;
}
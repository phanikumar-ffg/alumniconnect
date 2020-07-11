package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import com.drrf.alumniconnect.model.JobInformation;
import java.util.List;

public interface JobInformationService {

	public List<JobInformation> getJobs(Long studentId) throws Exception;
	public String saveJobEntryDetails(JobInformation jobInformation) throws JobInformationDaoException;

}

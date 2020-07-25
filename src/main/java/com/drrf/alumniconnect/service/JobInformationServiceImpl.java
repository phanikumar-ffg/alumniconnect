package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.drrf.alumniconnect.dao.JobInformationDao;
import com.drrf.alumniconnect.model.JobInformation;
import com.drrf.alumniconnect.model.JobRequest;

@Service
public class JobInformationServiceImpl implements JobInformationService {
	@Autowired
	JobInformationDao jobInformationDao;

	@Override
	public List<JobInformation> getJobs(Long studentId) throws Exception{
		return jobInformationDao.getJobs(studentId);
	}

	@Override
	public List<JobInformation> getAllJobs() throws Exception{
		return jobInformationDao.getAllJobs();
	}

	@Override
	public String saveJobEntryDetails(JobInformation jobInformation) throws JobInformationDaoException {
		jobInformationDao.saveJobEntryDetails(jobInformation);
		return "success";
	}

	@Override
	public String deleteJobEntry(JobRequest jobInformation) throws JobInformationDaoException {
		return jobInformationDao.deleteJobEntry(jobInformation);
	}

}

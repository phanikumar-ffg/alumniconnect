package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.drrf.alumniconnect.dao.JobInformationDao;
import com.drrf.alumniconnect.model.JobInformation;

@Service
public class JobInformationServiceImpl implements JobInformationService {
	@Autowired
	JobInformationDao JobInfoDao;

	@Override
	public List<JobInformation> getJobs(Long student_id) throws Exception{
		return JobInfoDao.getJobs(student_id);
	}

	@Override
	public String saveJobEntryDetails(JobInformation jobInformation) throws JobInformationDaoException {
		JobInfoDao.saveJobEntryDetails(jobInformation);
		return "success";
	}

}

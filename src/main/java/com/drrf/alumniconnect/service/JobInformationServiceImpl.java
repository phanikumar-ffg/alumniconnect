package com.drrf.alumniconnect.service;

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
	public List<JobInformation> getJobs() throws Exception{
		return JobInfoDao.getJobs();
	}

}

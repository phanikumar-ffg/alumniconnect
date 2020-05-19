package com.drrf.alumniconnect.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drrf.alumniconnect.model.JobRequest;
import com.drrf.alumniconnect.dao.JobRequestDao;

@Service
public class JobRequestServiceImpl implements JobRequestService{

    @Autowired
    JobRequestDao jobReqDao;

    @Override
    public String sendJobRequest(JobRequest jobReq){
        return jobReqDao.sendJobRequest(jobReq);
    }

}
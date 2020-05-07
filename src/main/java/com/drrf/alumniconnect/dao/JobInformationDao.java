package com.drrf.alumniconnect.dao;

import java.util.List;
import com.drrf.alumniconnect.model.JobInformation;

public interface JobInformationDao {

    public List<JobInformation> getJobs() throws Exception;
}
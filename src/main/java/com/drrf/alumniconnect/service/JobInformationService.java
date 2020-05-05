package com.drrf.alumniconnect.service;

import com.drrf.alumniconnect.model.JobInformation;
import java.util.List;

public interface JobInformationService {

	public List<JobInformation> getJobs() throws Exception;

}

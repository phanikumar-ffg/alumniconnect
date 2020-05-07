package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.dao.JobInformationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


import com.drrf.alumniconnect.jdbcmapper.JobInformationRowMapper;
import com.drrf.alumniconnect.model.JobInformation;

@Repository
public class JobInformationDaoImpl implements JobInformationDao{
	private static final Logger logger = LoggerFactory.getLogger(JobInformationDao.class);
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<JobInformation> getJobs() throws Exception{
		List<JobInformation> jobs = null;

        try {
            final String get_all_jobs = "SELECT * FROM tbl_job_information";

            jobs = jdbcTemplate.query(get_all_jobs, new JobInformationRowMapper());

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw e;
		}

		return jobs;
		
	}

}
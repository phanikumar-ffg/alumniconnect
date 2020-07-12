package com.drrf.alumniconnect.jdbcmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.JobInformation;

public class JobInformationRowMapper implements RowMapper<JobInformation> {

	@Override
	public JobInformation mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		 JobInformation jobs = new JobInformation();

		 jobs.setJobId(resultSet.getLong("JOB_ID"));
		 jobs.setCompanyName(resultSet.getString("COMPANY_NAME"));
		 jobs.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
		 jobs.setdesignation(resultSet.getString("DESIGNATION"));
		 jobs.setCityName(resultSet.getString("CITY_NAME"));
         return jobs;
	}

	
}

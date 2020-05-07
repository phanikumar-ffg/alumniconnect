package com.drrf.alumniconnect.jdbcmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.JobInformation;

public class JobInformationRowMapper implements RowMapper<JobInformation> {

	@Override
	public JobInformation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		 JobInformation jobs = new JobInformation();
		 
		 jobs.setJobId(resultSet.getLong("jobid"));
		 jobs.setCompanyName(resultSet.getString("companyName"));
		 jobs.setRole(resultSet.getString("role"));
		 jobs.setJobDescription(resultSet.getString("jobDescription"));
         jobs.setCity(resultSet.getString("city"));
         jobs.setState(resultSet.getString("state"));
         jobs.setSalaryScale(resultSet.getString("salaryScale"));
           
         return jobs;
	}

	
}

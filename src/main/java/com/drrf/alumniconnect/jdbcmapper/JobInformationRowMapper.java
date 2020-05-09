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
		 jobs.setJobDescription(resultSet.getString("jobDescription"));
		 jobs.setDesignation(resultSet.getString("DESIGNATION"));
		 jobs.setCityId(resultSet.getLong("CITY_ID"));
		 jobs.setVacancyCount(resultSet.getLong("VACANCY_COUNT"));
		 jobs.setQualificationReq(resultSet.getString("QUALIFICATION_REQ"));
		 jobs.setCreateDate(resultSet.getTimestamp("CREATE_TIMESTAMP"));
           
         return jobs;
	}

	
}

package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.dao.JobInformationDao;
import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


import com.drrf.alumniconnect.jdbcmapper.JobInformationRowMapper;
import com.drrf.alumniconnect.model.JobInformation;

@Repository
public class JobInformationDaoImpl implements JobInformationDao{
	private static final Logger logger = LoggerFactory.getLogger(JobInformationDao.class);
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<JobInformation> getJobs(Long student_id) throws Exception{
		List<JobInformation> jobs = null;

        try {
            final String get_all_jobs = "select tbl_job_information.JOB_ID, tbl_job_information.COMPANY_NAME, tbl_job_information.DESIGNATION, tbl_job_information.JOB_DESCRIPTION, tbl_city_details.CITY_NAME from tbl_job_information INNER Join tbl_city_details on tbl_job_information.CITY_ID = tbl_city_details.CITY_ID where tbl_job_information.JOB_ID not in (select JOB_ID from tbl_job_application_status where STUDENT_ID = "+student_id+")";

			jobs = jdbcTemplate.query(get_all_jobs, new JobInformationRowMapper());

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw e;
		}

		return jobs;
		
	}
	public String saveJobEntryDetails(JobInformation jobInformation) throws JobInformationDaoException {
		try {
			String sql = "INSERT INTO tbl_job_information (COMPANY_NAME,DESIGNATION,JOB_DESCRIPTION,CITY_ID,VACANCY_COUNT,QUALIFICATION_REQ,CREATE_TIMESTAMP) VALUES ('" + jobInformation.getCompanyName() + "','" + jobInformation.getDesignation() +"','" + jobInformation.getJobDescription() + "','" + jobInformation.getCityId() + "','" + jobInformation.getVacancyCount() + "','" + jobInformation.getQualificationReq() + "','"+ jobInformation.getCreateDate() +"')";
			int i = jdbcTemplate.update(sql);
			if(i==0){
				throw new JobInformationDaoException("Error occurred while saving Job Details"+jobInformation.getJobId());
			}
			else {
				return "success";
			}
		}
		catch (JobInformationDaoException e) {
			throw e;
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new JobInformationDaoException( "Error occured while saving job details" +jobInformation.getJobId());
		}

	}

}

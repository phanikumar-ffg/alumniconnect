package com.drrf.alumniconnect.dao;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.drrf.alumniconnect.model.JobRequest;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
 
@Repository
public class JobRequestDaoImpl implements JobRequestDao{
	private static final Logger logger = LoggerFactory.getLogger(JobRequestDaoImpl.class);
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	MailService mailService;

	@Override
	public String sendJobRequest(JobRequest jobReq) {

        String message = "";
		try {
			
			if (jobReq == null ) {
				throw new Exception("No Request Data Found");
			}else {
				String emailBody= "An interest has been expressed for the following job opening by "+ 
						jobReq.getStudentName().toUpperCase()+" (Aspirant id - "+jobReq.getStudentId()+")\n\n"
						+"Job ID: "+ jobReq.getJobId() + "\nCompany Name: "+ jobReq.getJobCompanyName()+
						"\nRole/Designation: "+ jobReq.getJobRole() +"\nJob Description: "+ jobReq.getJobDescription()
						+"\n\nRegards,\nTeam DRF Grow"
                        ;
				
		        Mail mail = new Mail();
		        mail.setMailFrom(APIUtils.MAIL_FROM);
		        mail.setMailTo(APIUtils.MAIL_TO);
		        mail.setMailSubject(APIUtils.MAIL_JOB_REQ_SUB);
				mail.setMailContent(emailBody);
				mail.setMailCc(jobReq.getStudentEmail());
				try {
					logger.info("Sending mail to: "+jobReq.getStudentEmail());
					mailService.sendEmail(mail);
					logger.info("Mail successfully sent");
					this.makeDBInsert(jobReq);
					logger.info("DB insert for job request was Successful");
					message = "Job Request Successfully sent!!";
					return message;
				}
				catch(Exception e){
					logger.error("Error: "+e.getLocalizedMessage());
					message = "Sending Job Request Failed";
					return message;
				}

		    }
		}catch(Exception e) {
			logger.error("Error: "+e.getLocalizedMessage());
			return message;
        }
	}

	public void makeDBInsert(JobRequest jobReq) throws Exception{
		try {
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			Timestamp sqlTime=new Timestamp(date.getTime());
			logger.info("Inserting job request details: "+jobReq.getStudentId()+","+jobReq.getJobId());
			String sql = "INSERT INTO tbl_job_application_status (ASPIRANT_ID, JOB_ID, APPLICATION_STATUS, DATE_OF_SELECTION, TIMESTAMP) VALUES (?,?,?,?,?)";
			int i = jdbcTemplate.update(sql, new Object[] { jobReq.getStudentId(), jobReq.getJobId(), "Submitted", sqlDate, sqlTime });
			if(i==0){
				throw new Exception( "Error occured while saving job request details");
			}
		}
		catch(Exception e){
			logger.error("Error: "+e.getLocalizedMessage());
			throw new Exception( "Error occured while saving job request details");
		}

	}

}

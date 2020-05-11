package com.drrf.alumniconnect.dao;

import java.util.Date;
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
				this.makeDBInsert(jobReq);
				String emailBody= "New Job Request\n\nPlease find the details below.\n\n\n"
                        +"User ID: "+jobReq.getStudentId()+" \nStudent_Name: "+jobReq.getStudentName()+
                        "\nJob_ID: "+ jobReq.getJobId()
                        ;
				
		        Mail mail = new Mail();
		        mail.setMailFrom(APIUtils.MAIL_FROM);
		        mail.setMailTo(APIUtils.MAIL_TO);
		        mail.setMailSubject(APIUtils.MAIL_JOB_REQ_SUB);
		        mail.setMailContent(emailBody);
		        mailService.sendEmail(mail);
		        message = "Job Request Successfully sent!!";
		    }
		}catch(Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            message = "Sending Job Request Failed!!";
        }
        return message;
	}

	public void makeDBInsert(JobRequest jobReq) throws Exception{
		try {
			String sql = "INSERT INTO tbl_job_application_status (STUDENT_ID, JOB_ID, APPLICATION_STATUS, CREATE_TIMESTAMP) VALUES (?,?,?,?)";
			int i = jdbcTemplate.update(sql, new Object[] { jobReq.getStudentId(), jobReq.getJobId(), "Submitted", new Date() });
			if(i==0){
				throw new Exception( "Error occured while saving job request details");
			}
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new Exception( "Error occured while saving job request details");
		}

	}

}

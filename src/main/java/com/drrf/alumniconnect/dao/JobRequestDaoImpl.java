package com.drrf.alumniconnect.dao;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.drrf.alumniconnect.model.JobRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.*;
import com.drrf.alumniconnect.utils.APIUtils;
 
@Repository
public class JobRequestDaoImpl implements JobRequestDao{
	private static final Logger logger = LoggerFactory.getLogger(JobRequestDaoImpl.class);
	@Autowired
    private JdbcTemplate jdbcTemplate;

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

	private static void sendMail(final Mail mail) throws Exception{
		final SendGrid sg = new SendGrid(APIUtils.SENDGRID_API_KEY);
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());

		Response response = sg.api(request);
		logger.info(String.valueOf(response.getStatusCode()));
		logger.info(response.getBody());
		logger.info(String.valueOf(response.getHeaders()));
 	 }

	 public static Mail mailBuilder(JobRequest jobReq) {
		 String emailBody= "An interest has been expressed for the following job opening by "+ 
						jobReq.getStudentName().toUpperCase()+" (Aspirant id - "+jobReq.getStudentId()+")\n\n"
						+"Job ID: "+ jobReq.getJobId() + "\nCompany Name: "+ jobReq.getJobCompanyName()+
						"\nRole/Designation: "+ jobReq.getJobRole() +"\nJob Description: "+ jobReq.getJobDescription()
						+"\n\nRegards,\nTeam DRF Grow"
                        ;

		Mail mail = new Mail();

		Email fromEmail = new Email(APIUtils.MAIL_FROM);
		Content content = new Content("text/plain", emailBody);
		Personalization personalization = new Personalization();
		Email to = new Email(APIUtils.MAIL_TO);
		personalization.addTo(to);
		Email cc = new Email(jobReq.getStudentEmail());
		personalization.addCc(cc);

		mail.setFrom(fromEmail);
		mail.setSubject(APIUtils.MAIL_JOB_REQ_SUB);
		mail.addContent(content);
		mail.addPersonalization(personalization);
		return mail;
  	}

  @Override
	public String sendJobRequest(JobRequest jobReq) {

        String message = "";
		try {
			
			if (jobReq == null ) {
				throw new Exception("No Request Data Found");
			}else {

				try {
					logger.info("Sending mail to: "+jobReq.getStudentEmail());
					final Mail jobReqMail = mailBuilder(jobReq);
					sendMail(jobReqMail);
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

}

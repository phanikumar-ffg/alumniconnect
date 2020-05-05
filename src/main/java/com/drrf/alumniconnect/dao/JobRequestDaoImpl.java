package com.drrf.alumniconnect.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.model.JobRequest;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
 
@Repository
public class JobRequestDaoImpl implements JobRequestDao {
	private static final Logger logger = LoggerFactory.getLogger(JobRequestDaoImpl.class);

	@Autowired
	MailService mailService;

	@Override
	public String sendJobRequest(JobRequest jobReq) {

        String message = "";
		try {
			
			if (jobReq == null ) {
				throw new Exception("No Request Data Found");
			}else {
				
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

}

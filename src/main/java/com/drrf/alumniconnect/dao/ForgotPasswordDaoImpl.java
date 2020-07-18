package com.drrf.alumniconnect.dao;

import javax.mail.AuthenticationFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.jdbcmapper.LoginDetailsRowMapper;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
 
@Repository
public class ForgotPasswordDaoImpl implements ForgotPasswordDao {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	MailService mailService;

	@SuppressWarnings("unused")
	@Override
	public String getForgotPasswordInformation(String email) throws ForgotPasswordDaoException, AuthenticationFailedException{

		//List<LoginDetails> loginDetailsList = new ArrayList<LoginDetails>();
		LoginDetails loginDetails = null;
		String message = "";

		try {
			String sql = "SELECT sr_no, email_id,password FROM tbl_login_details where email_id = ?";

			loginDetails = jdbcTemplate.queryForObject(sql, new Object[]{email}, new LoginDetailsRowMapper());


			if (loginDetails == null ) {
				throw new ForgotPasswordDaoException( String.format("User [%s]  information not available in the database", email));
			}else {
				//write the code to send details to email and phone
				//need smtp details
				String emailBody= "Dr Reddy's Foundation welcome you,\n\nYour request for password deteails is successful. Please find the details below.\n\n\n"
						+ "User ID: "+loginDetails.getEmailId()+" \nPassword: "+loginDetails.getPassword()+"\n \n Regards,\n Dr Reddy Foundation";

		        Mail mail = new Mail();
		        mail.setMailFrom(APIUtils.MAIL_FROM);
		        mail.setMailTo(APIUtils.MAIL_TO);
		        mail.setMailSubject(APIUtils.MAIL_FORGOT_PWD_SUB);
		        mail.setMailContent(emailBody);
		        mailService.sendEmail(mail);

				message ="Your login ID and password is sent to your email ID:"+ loginDetails.getEmailId() +" and registered mobile number. If you don't get the details in 5 min, please contact the admin. Admin E-mail ID: abc@gmail.com";
			}
		} catch (ForgotPasswordDaoException e) {
			throw e;
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage(),e);
			throw new ForgotPasswordDaoException( "Error occured while checking the user account information " +  email);
		}

		return message;
	}

}

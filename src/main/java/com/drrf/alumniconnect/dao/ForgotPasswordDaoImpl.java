package com.drrf.alumniconnect.dao;

import javax.mail.AuthenticationFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.jdbcmapper.LoginDetailsRowMapper;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.utils.APIUtils;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Repository
public class ForgotPasswordDaoImpl implements ForgotPasswordDao {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unused")
	@Override
	public String getForgotPasswordInformation(String email) throws ForgotPasswordDaoException, AuthenticationFailedException{

		//List<LoginDetails> loginDetailsList = new ArrayList<LoginDetails>();
		LoginDetails loginDetails = null;
		String message = "";

		try {
			String sql = "SELECT sr_no, email_id,password FROM tbl_login_details where email_id = ?";

			loginDetails = jdbcTemplate.queryForObject(sql, new Object[]{email}, new LoginDetailsRowMapper());
			//write the code to send details to email and phone
			//need smtp details
			logger.info("User found and getting ready to send email.");
			String emailBody= "Dr Reddy's Foundation welcome you,\n\nYour request for password deteails is successful. Please find the details below.\n\n\n"
					+ "User ID: "+loginDetails.getEmailId()+" \nPassword: "+loginDetails.getPassword()+"\n \n Regards,\n Dr Reddy Foundation";


			Email from = new Email(APIUtils.MAIL_FROM);
			String subject = APIUtils.MAIL_FORGOT_PWD_SUB;
			Email to = new Email(loginDetails.getEmailId());
			Content content = new Content("text/plain", emailBody);
			Mail mail = new Mail(from, subject, to, content);

			SendGrid sg = new SendGrid(APIUtils.SENDGRID_API_KEY);
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			logger.info(String.valueOf(response.getStatusCode()));
			logger.info(response.getBody());
			logger.info(String.valueOf(response.getHeaders()));
			logger.info("mail sent");
			message ="Your login ID and password is sent to your email ID "+ loginDetails.getEmailId() +" and registered mobile number. If you dont get the details in 5 min, please contact the admin. Admin E-mail ID abc@gmail.com";
		}catch (EmptyResultDataAccessException e){
			throw new ForgotPasswordDaoException( String.format("User information not available in the database"));
		} catch(Exception e) {
			logger.error(e.getLocalizedMessage(),e);
			throw new ForgotPasswordDaoException( "Error occured while checking the user account information " +  email);
		}

		return message;
	}

}

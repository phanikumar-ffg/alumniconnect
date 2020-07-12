package com.drrf.alumniconnect.dao;


import com.drrf.alumniconnect.exceptions.RegistrationDaoException;
import com.drrf.alumniconnect.jdbcmapper.CentreDetailsRowMapper;
import com.drrf.alumniconnect.jdbcmapper.LoginDetailsRowMapper;
import com.drrf.alumniconnect.jdbcmapper.UserProfileRowMapper;
import com.drrf.alumniconnect.model.*;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class RegistrationDaoImpl implements RegistrationDao{

    private static final Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    MailService mailService;
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generatePassword(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
    @Override
    public UserProfile newUserRegistration(UserProfile userProfile) throws RegistrationDaoException {
        UserProfile userProfile1 = null;
        LoginDetails loginDetails;


        final String check_login_details = "select * from tbl_login_details where email_id=?";
        try {
            loginDetails = jdbcTemplate.queryForObject(check_login_details, new Object[]{userProfile.getEmailId()}, new LoginDetailsRowMapper());
            logger.info("Account already exists for email id:"+userProfile.getEmailId());

            return null;
        }
        catch (EmptyResultDataAccessException e) {
            logger.info(e.getLocalizedMessage()+": while collecting login details");
            final String check_profile_details = "select * from tbl_profile_data pd inner join tbl_centre_details cd on (pd.centre_id= cd.centre_id) inner join tbl_city_details ctd on (pd.city_id = ctd.city_id) where pd.aspirant_id=? and pd.first_name=? and pd.dob=?";
            try {
                userProfile1 = jdbcTemplate.queryForObject(check_profile_details, new Object[]{userProfile.getAspirantId(),userProfile.getFirstName(),/*userProfile.getCenterId(),*/new SimpleDateFormat("yyyy-MM-dd").format(userProfile.getDob())}, new UserProfileRowMapper());
                String dt=new SimpleDateFormat("yyyy-MM-dd").format(userProfile.getDob());

                logger.info("Profile Details Matched...Creating account for "+userProfile.getEmailId());
                    final String insert_login_details = "insert into tbl_login_details(email_id,password,create_timestamp,update_timestamp) values(?,?,?,?)";

                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    String strDateFormat = "YYYY-MM-DD hh:mm:ss a";
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = dateFormat.format(date);
                    String pass = generatePassword(8);
                    //System.out.println(pass);
                    jdbcTemplate.update(insert_login_details, userProfile.getEmailId(), pass, formattedDate, formattedDate);
                    String emailBody = "New User Registration\n\nPlease find the details below.\n\n\n"
                            + "User ID: " + userProfile.getAspirantId() + " \nPassword: " + pass;
                logger.info("Account created for user "+userProfile.getEmailId());
                    Mail mail = new Mail();
                    mail.setMailFrom(APIUtils.MAIL_FROM);
                    mail.setMailTo(/*userProfile1.getEmail()*/APIUtils.MAIL_TO);
                    mail.setMailSubject(APIUtils.MAIL_NEW_USR_SUB);
                    mail.setMailContent(emailBody);
                    mailService.sendEmail(mail);
                logger.info( "User Account created Successfully!!Details sent to registered mail "+userProfile.getEmailId());
                    return userProfile1;
            }
            catch (EmptyResultDataAccessException ex) {
                logger.info(ex.getLocalizedMessage()+": while collecting profile details");
                return null;
            } catch (Exception e1){
                logger.error(e1.getLocalizedMessage(),e1);
                throw new RegistrationDaoException("Error occured while saving userDetails for user:"+userProfile.getAspirantId()+"."+e1.getLocalizedMessage());
            }
        }
    }

    @Override
    public List<CentreDetails> getCentres() throws Exception {
        try {
            logger.info("Getting centre details from db");
            final String centre_details = "select * from tbl_centre_details";
            List<CentreDetails> list = jdbcTemplate.query(centre_details,  new CentreDetailsRowMapper());
            return list;
        }catch (Exception e)
        { throw e;}
    }
}

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
        final String check_profile_details = "select * from tbl_profile_data pd inner join tbl_centre_details cd on (pd.centre_id= cd.centre_id) inner join tbl_city_details ctd on (pd.city_id = ctd.city_id) where (pd.aspirant_id=? and pd.email_id=?) or (pd.aspirant_id=? and pd.phone=?) or (pd.email_id=? and pd.phone=?)";
        try {
            userProfile1 = jdbcTemplate.queryForObject(check_profile_details, new Object[]{userProfile.getAspirantId(), userProfile.getEmailId(), userProfile.getAspirantId(), userProfile.getPhone(), userProfile.getEmailId(), userProfile.getPhone(),/*new SimpleDateFormat("yyyy-MM-dd").format(userProfile.getDob())*/}, new UserProfileRowMapper());
            logger.info("Profile Details Matched...Creating account for " + userProfile1.getEmailId());
            final String check_login_details = "select * from tbl_login_details where email_id=?";
            try {
                loginDetails = jdbcTemplate.queryForObject(check_login_details, new Object[]{userProfile.getEmailId()}, new LoginDetailsRowMapper());
                logger.info("Account already exists for email id:" + userProfile1.getEmailId());

                return null;
            } catch (EmptyResultDataAccessException e) {
                String dt = new SimpleDateFormat("yyyy-MM-dd").format(userProfile1.getDob());
                final String insert_login_details = "insert into tbl_login_details(email_id,password,create_timestamp,update_timestamp) values(?,?,?,?)";
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();
                String strDateFormat = "YYYY-MM-DD hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(date);
                String pass = generatePassword(8);
                //System.out.println(pass);
                jdbcTemplate.update(insert_login_details, userProfile1.getEmailId(), pass, formattedDate, formattedDate);
                    /*String emailBody = "New User Registration\n\nPlease find the details below.\n\n\n"
                            + "User ID: " + userProfile.getAspirantId() + " \nPassword: " + pass;*/
                String emailBody = "Dear " + userProfile1.getFirstName() + ",\n\n" +
                        "Your Registration for the DRF application is successful and your login credentials are as below." +
                        "Please reset your password after the initial login using the change password functionality.\n\n"
                        + "Login Id - " + userProfile1.getEmailId() +
                        "\nPwd - " + pass + "\n\nRegards\nTeam DRF Grow\n";
                logger.info("Account created for user " + userProfile1.getEmailId());
                Mail mail = new Mail();
                mail.setMailFrom(APIUtils.MAIL_FROM);
                mail.setMailTo(/*userProfile1.getEmail()*/APIUtils.MAIL_TO);
                mail.setMailSubject(APIUtils.MAIL_NEW_USR_SUB);
                mail.setMailContent(emailBody);
                mailService.sendEmail(mail);
                logger.info("User Account created Successfully!!Details sent to registered mail " + userProfile1.getEmailId());
                return userProfile1;
            }
            catch (Exception e1){
                logger.error(e1.getLocalizedMessage(),e1);
                throw new RegistrationDaoException("Error occured while saving userDetails for user:"+userProfile1.getAspirantId()+"."+e1.getLocalizedMessage());
            }
        }
        catch (EmptyResultDataAccessException ex) {
            logger.info(ex.getLocalizedMessage()+": while collecting profile details.Profile Details doesn't match");
            return null;

        }
        catch (Exception e1){
            logger.error(e1.getLocalizedMessage(),e1);
            throw new RegistrationDaoException("Error occured while checking profile details user:"+userProfile.getAspirantId()+"."+e1.getLocalizedMessage());
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

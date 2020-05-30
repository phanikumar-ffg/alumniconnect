package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.jdbcmapper.JobInformationRowMapper;
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

import javax.mail.AuthenticationFailedException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Repository
public class RegistrationDaoImpl implements RegistrationDao{

    private static final Logger logger = LoggerFactory.getLogger(JobInformationDao.class);
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
    public UserProfile newUserRegistration(UserProfile userProfile) throws ForgotPasswordDaoException, AuthenticationFailedException {
        UserProfile userProfile1 = null;
        LoginDetails loginDetails;

        String message="";

        final String check_login_details = "select * from tbl_login_details where student_id=?";
        try {
            loginDetails = jdbcTemplate.queryForObject(check_login_details, new Object[]{userProfile.getStudentId()}, new LoginDetailsRowMapper());
            return null;
        }
        catch (EmptyResultDataAccessException e) {
            final String check_profile_details = "select * from tbl_profile_data where student_id=? and first_name=? and centre_id=? and date_of_birth=?";
            try {
                userProfile1 = jdbcTemplate.queryForObject(check_profile_details, new Object[]{userProfile.getStudentId(),userProfile.getFirstName(),userProfile.getCenterId(),new SimpleDateFormat("yyyy-MM-dd").format(userProfile.getDob())}, new UserProfileRowMapper());
                String dt=new SimpleDateFormat("yyyy-MM-dd").format(userProfile.getDob());
                System.out.println(dt.compareTo(userProfile1.getDob().toString()));
                    final String insert_login_details = "insert into tbl_login_details() values(?,?,?,?,?)";
                    //Date date= new Date();
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    String strDateFormat = "YYYY-MM-DD hh:mm:ss a";
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = dateFormat.format(date);
                    String pass = generatePassword(8);
                    //System.out.println(pass);
                    jdbcTemplate.update(insert_login_details, userProfile.getStudentId(), userProfile.getFirstName(), pass, formattedDate, formattedDate);
                    String emailBody = "New User Registration\n\nPlease find the details below.\n\n\n"
                            + "User ID: " + userProfile.getStudentId() + " \nPassword: " + pass;

                    Mail mail = new Mail();
                    mail.setMailFrom(APIUtils.MAIL_FROM);
                    mail.setMailTo(userProfile1.getEmail());
                    mail.setMailSubject(APIUtils.MAIL_NEW_USR_SUB);
                    mail.setMailContent(emailBody);
                    mailService.sendEmail(mail);
                    message = "User Account created Successfully!!";
                    return userProfile1;
            }
            catch (EmptyResultDataAccessException ex) {
                return null;
            }
        }
    }
}

package com.drrf.alumniconnect.dao;


import com.drrf.alumniconnect.exceptions.HelpHistoryDaoException;
import com.drrf.alumniconnect.jdbcmapper.*;
import com.drrf.alumniconnect.model.HelpDetails;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class HelpHistoryDaoImpl implements HelpHistoryDao {
    private static final Logger logger = LoggerFactory.getLogger(HelpHistoryDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    MailService mailService;

    public String saveHelpDetails(HelpHistory helpHistory) throws HelpHistoryDaoException {
        List<UserProfile> userProfile1 = null;
        try {
            java.util.Date date=new java.util.Date();
            Timestamp sqlTime=new Timestamp(date.getTime());
            logger.info("Inserting a new help request with Reason{}, Details{}, Description{} for student Id{}",helpHistory.getReason(), helpHistory.getDetails(),helpHistory.getDescription(),helpHistory.getAspirantId());
            String sql = "INSERT INTO tbl_help_history (ASPIRANT_ID,REASON,DETAILS,CENTRE_ID,CREATE_TIMESTAMP,DESCRIPTION) VALUES (?,?,?,?,?,?)";
            int i = jdbcTemplate.update(sql, new Object[] { helpHistory.getAspirantId(), helpHistory.getReason(), helpHistory.getDetails(), helpHistory.getCenterId(), sqlTime,helpHistory.getDescription() });
            //  String st = "INSERT INTO DRF.TBL_ADMIN_HELP_REQUEST_STATUS (STATUS,CREATE_TIMESTAMP,STUDENT_ID) VALUES(?,?,?)";
            //jdbcTemplate.update(st,new Object[] {"New",sqlTime,helpHistory.getStudentId()});
          // String sql2 = "select pd.FIRST_NAME from tbl_profile_data pd inner join tbl_help_history hd on  (pd.aspirant_id = hd.aspirant_id) where hd.aspirant_id=?";
             String sql2="select * from tbl_profile_data where aspirant_Id="+helpHistory.getAspirantId();
           userProfile1 = jdbcTemplate.query(sql2, new UserNameRowMapper());
            // userProfile1 = jdbcTemplate.queryForObject(sql2,new Object[]{ helpHistory.getAspirantId()}, new UserProfileRowMapper());
            System.out.println("Display user name"+ userProfile1.get(0).getFirstName()+"Display Last name"+userProfile1.get(0).getLastName());
            logger.info("The value of i"+i);
            if (i == 0) {
                throw new HelpHistoryDaoException("Error occured while saving user help information" + helpHistory.getAspirantId());
            } else {
               String emailBody = "Dear Admin,\n\n A help request has been initialized by"+userProfile1.get(0).getFirstName()+userProfile1.get(0).getLastName()+" (Aspirant Id-" + + helpHistory.getAspirantId() + ") and the details of the request are as follows: \n\n Problem Type-" + helpHistory.getReason() + "\n Problem Description-" + helpHistory.getDetails() + "\n Additional Details-" + helpHistory.getDescription() + "\n \n Regards,\n Team Dr Reddy Foundation";

                Mail mail = new Mail();
                mail.setMailFrom(APIUtils.MAIL_FROM);
                mail.setMailTo(APIUtils.MAIL_TO);
                mail.setMailSubject(APIUtils.MAIL_HELP_REQUEST);
                mail.setMailContent(emailBody);
                mailService.sendEmail(mail);
                logger.info("Detaiils inserted successfully");
                return "success";
            }

        } catch (HelpHistoryDaoException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new HelpHistoryDaoException("Error occured while saving help details" + helpHistory.getAspirantId());
        }
    }



    public List<HelpDetails> getHelpDetails(String helpType) throws HelpHistoryDaoException{
        try {
            String sql = "SELECT help_id,help_type,help_value,create_timestamp FROM tbl_help_details  where "+helpType;

            List<HelpDetails> list = jdbcTemplate.query(sql,  new HelpDetailsRowMapper());
            if(list.size()==0){

                throw  new HelpHistoryDaoException(String.format("Help Details[%s] not found",helpType));
            }
            else {
                logger.info("Details fetched successfully for Problem Type and Problem Details dropdowns");
                return list;
            }
        }catch (HelpHistoryDaoException e)
        {
            logger.error("Error occurred in fetching details",e.getLocalizedMessage(), e);
            throw e;}
    }

}

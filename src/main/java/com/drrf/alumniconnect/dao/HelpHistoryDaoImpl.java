package com.drrf.alumniconnect.dao;


import com.drrf.alumniconnect.exceptions.HelpHistoryDaoException;
import com.drrf.alumniconnect.jdbcmapper.HelpDetailsRowMapper;
import com.drrf.alumniconnect.jdbcmapper.LoginDetailsRowMapper;
import com.drrf.alumniconnect.model.HelpDetails;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HelpHistoryDaoImpl implements HelpHistoryDao {
    private static final Logger logger = LoggerFactory.getLogger(HelpHistoryDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    MailService mailService;

    public String saveHelpDetails(HelpHistory helpHistory) throws HelpHistoryDaoException {
        try {
            String sql = "INSERT INTO tbl_help_history (STUDENT_ID,REASON,DETAILS,CENTRE_ID,CREATE_TIMESTAMP,DESCRIPTION) VALUES ('" + helpHistory.getStudentId() + "','" + helpHistory.getReason() + "','" + helpHistory.getDetails() + "','" + helpHistory.getCenterId() + "','" + helpHistory.getCreateDate() + "','" + helpHistory.getDescription() + "')";
            int i = jdbcTemplate.update(sql);

            System.out.println("The value of i");
            if (i == 0) {
                throw new HelpHistoryDaoException("Error occured while saving user help information" + helpHistory.getStudentId());
            } else {
                String emailBody = "Dr Reddy's Foundation,\n\n Received a Help Request from a student \n\n\n" + helpHistory.getStudentId() + "\n\n Reason" + helpHistory.getReason() + "\n\n Details " + helpHistory.getDetails() + "\n\n Description" + helpHistory.getDescription() + "\n \n Regards,\n Dr Reddy Foundation";

                Mail mail = new Mail();
                mail.setMailFrom(APIUtils.MAIL_FROM);
                mail.setMailTo(APIUtils.MAIL_TO);
                mail.setMailSubject(APIUtils.MAIL_HELP_REQUEST);
                mail.setMailContent(emailBody);
                mailService.sendEmail(mail);
                return "success";
            }

        } catch (HelpHistoryDaoException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new HelpHistoryDaoException("Error occured while saving help details" + helpHistory.getStudentId());
        }
    }



    public List<HelpDetails> getHelpDetails(String helpType) throws HelpHistoryDaoException{
        HelpDetails helpDetails=null;
        String message="";
        try {
            String sql = "SELECT help_id,help_type,help_value,create_timestamp FROM tbl_help_details  where "+helpType;

            List<HelpDetails> list = jdbcTemplate.query(sql,  new HelpDetailsRowMapper());
            if(list.size()==0){
                throw  new HelpHistoryDaoException(String.format("Help Details[%s] not found",helpType));
            }
            else {
                return list;
            }
        }catch (HelpHistoryDaoException e)
        { throw e;}
    }

}

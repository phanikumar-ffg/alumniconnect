package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.jdbcmapper.ContentManagementRowMapper;
import com.drrf.alumniconnect.jdbcmapper.RegisteredUsersForNotificationsRowMapper;
import com.drrf.alumniconnect.model.ContentManagement;
import com.drrf.alumniconnect.model.UserDetailsForNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Repository
public class SaveUserTokenDaoImpl implements SaveUserTokenDao{
    private static final Logger logger = LoggerFactory.getLogger(SaveUserTokenDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
    @Override
    public String saveUserToken(String userToken) throws ContentNotFoundDaoException {
        try {
            if (!(userToken.isEmpty()) && userToken!= null && userToken.contains("ExponentPushToken[")) {
                if(countRecords(userToken) == 0 ) {
                    logger.info("Onboarding a new User at time {}", timestamp);
                    String sql = "INSERT INTO TBL_REGISTERED_USERS_FOR_NOTIFICATIONS (USER_TOKEN, CREATE_TIMESTAMP) VALUES ('" + userToken + "','"
                            + timestamp + "')";
                    int i = jdbcTemplate.update(sql);
                    if (i == 0) {
                        throw new ContentNotFoundDaoException("Error occurred while saving Token information");
                    } else {
                        return "Request saved to database successfully";
                    }
                } else{
                    logger.info( "Failed: Record Found in Database with same Token Value");
                    return "Failed: Record Found in Database with same Token Value";
                }
            } else{
                return "Error occurred: Token is invalid, Please check";
            }
        } catch (ContentNotFoundDaoException e) {
            logger.error("Error occurred while saving the information", e.getMessage());
            return "Error occurred while saving the information";
        }
    }
    public Integer countRecords(String userToken){
        String frequency = "SELECT(COUNT(*)) FROM TBL_REGISTERED_USERS_FOR_NOTIFICATIONS where USER_TOKEN = ?";
        return jdbcTemplate.queryForObject(frequency, new Object[]{userToken}, Integer.class);
    }
    public List<String> getAllUserToken(){
        List<UserDetailsForNotification> userDetailsForNotificationList = null;
        List<String> userTokenList = new ArrayList<>();
        try {
            final String get_all_user_details = "SELECT * FROM TBL_REGISTERED_USERS_FOR_NOTIFICATIONS where CREATE_TIMESTAMP is NOT NULL";
            userDetailsForNotificationList = jdbcTemplate.query(get_all_user_details, new RegisteredUsersForNotificationsRowMapper());
            Iterator<UserDetailsForNotification> userDetailIterator = userDetailsForNotificationList.iterator();
            while(userDetailIterator.hasNext()) {
                userTokenList.add(userDetailIterator.next().getUserToken());

            }
        } catch (CannotGetJdbcConnectionException e) {
            logger.error("Error occurred while fetching the information from content management table: ",e.getMessage());
        }
        return userTokenList;
    }
}

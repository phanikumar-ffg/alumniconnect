package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.ContentManagement;
import com.drrf.alumniconnect.model.UserDetailsForNotification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisteredUsersForNotificationsRowMapper implements RowMapper<UserDetailsForNotification> {

    @Override
    public UserDetailsForNotification mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        UserDetailsForNotification userDetailsForNotification = new UserDetailsForNotification();

        userDetailsForNotification.setNotificationID(resultSet.getInt("NOTIFICATION_ID"));
        userDetailsForNotification.setUserToken(resultSet.getString("USER_TOKEN"));
        userDetailsForNotification.setTimestamp(resultSet.getTimestamp("CREATE_TIMESTAMP"));

        return userDetailsForNotification;
    }
}

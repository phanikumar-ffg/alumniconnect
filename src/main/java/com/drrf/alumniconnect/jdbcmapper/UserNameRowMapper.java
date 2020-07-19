package com.drrf.alumniconnect.jdbcmapper;

import com.drrf.alumniconnect.model.UserName;
import com.drrf.alumniconnect.model.UserProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNameRowMapper implements RowMapper<UserName> {
    @Override
    public UserName mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        UserName userProfile = new UserName();

        userProfile.setAspirantId(resultSet.getLong("ASPIRANT_ID"));
        userProfile.setFirstName(resultSet.getString("FIRST_NAME"));
        userProfile.setLastName(resultSet.getString("LAST_NAME"));

        return userProfile;
    }
}

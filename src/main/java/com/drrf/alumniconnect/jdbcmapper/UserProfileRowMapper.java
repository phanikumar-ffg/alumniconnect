package com.drrf.alumniconnect.jdbcmapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.UserProfile;

public class UserProfileRowMapper implements RowMapper<UserProfile> {

	@Override
	public UserProfile mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		UserProfile userProfile = new UserProfile();
		 
		userProfile.setAspirantId(resultSet.getLong("ASPIRANT_ID"));
		userProfile.setFirstName(resultSet.getString("FIRST_NAME"));
		userProfile.setLastName(resultSet.getString("LAST_NAME"));
		userProfile.setDob(resultSet.getDate("DOB"));
		userProfile.setPhone(resultSet.getLong("PHONE"));
		userProfile.setEmailId(resultSet.getString("EMAIL_ID"));
		userProfile.setCityId(resultSet.getLong("CITY_ID"));
		userProfile.setCityName(resultSet.getString("CITY_NAME"));
		userProfile.setCenterId(resultSet.getLong("CENTRE_ID"));
		userProfile.setCenterName(resultSet.getString("CENTRE_NAME"));
		userProfile.setCurrentOrganization(resultSet.getString("CURRENT_ORGANIZATION"));
		userProfile.setIsAdmin(resultSet.getString("IS_ADMIN"));
	    
		return userProfile;
	}

}

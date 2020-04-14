package com.drrf.alumniconnect.jdbcmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;

public class UserProfileRowMapper implements RowMapper<UserProfile> {

	@Override
	public UserProfile mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		UserProfile userProfile = new UserProfile();
		 
		userProfile.setStudentId(resultSet.getLong("STUDENT_ID"));
		userProfile.setFirstName(resultSet.getString("FIRST_NAME"));
		userProfile.setLastName(resultSet.getString("LAST_NAME"));
		userProfile.setMobile(resultSet.getLong("MOBILE_INT"));
		userProfile.setEmail(resultSet.getString("EMAIL_ADDRESS"));
		userProfile.setAddress(resultSet.getString("ADDRESS"));
		userProfile.setCityId(resultSet.getLong("CITY_ID"));
		userProfile.setDob(resultSet.getDate("DATE_OF_BIRTH"));
		userProfile.setCenterId(resultSet.getString("CENTRE_ID"));
		userProfile.setCreateDate(resultSet.getTimestamp("CREATE_TIMESTAMP"));
		userProfile.setUpdateDate(resultSet.getTimestamp("UPDATE_TIMESTAMP"));
	    
		return userProfile;
	}

	
}

package com.drrf.alumniconnect.jdbcmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.UserProfile;

public class ProfileInformationRowMapper implements RowMapper<UserProfile> {

	@Override
	public UserProfile mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		 UserProfile userProfile = new UserProfile();
		//  TO-DO get proper idetifiers from the table
		 userProfile.setStudentId(resultSet.getLong("studentId"));
		 userProfile.setFirstName(resultSet.getString("firstName"));
		 userProfile.setLastName(resultSet.getString("lastName"));
		 userProfile.setMobile(resultSet.getString("mobile"));
         userProfile.setEmail(resultSet.getString("email"));
         userProfile.setAddress(resultSet.getString("address"));
         userProfile.setCityId(resultSet.getLong("cityId"));
         userProfile.setCenterId(resultSet.getString("centerId"));
        //  TO-DO find the getDate method
         userProfile.setDob(resultSet.getLong("dob"));
         userProfile.setCreateDate(resultSet.getString("createDate"));
         userProfile.setUpdateDate(resultSet.getString("updateDate"));

         return userProfile;
	}

	
}

package com.drrf.alumniconnect.jdbcmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.LoginDetails;

public class LoginDetailsRowMapper implements RowMapper<LoginDetails> {

	@Override
	public LoginDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		 LoginDetails loginDetails = new LoginDetails();
		 
		 loginDetails.setStudentId(resultSet.getLong("student_id"));
		 loginDetails.setUserName(resultSet.getString("username"));
		 loginDetails.setPassword(resultSet.getString("password"));
		 loginDetails.setCreateDate(resultSet.getTimestamp("create_timestamp"));
		 loginDetails.setUpdateDate(resultSet.getTimestamp("update_timestamp"));
	        return loginDetails;
	}

	
}

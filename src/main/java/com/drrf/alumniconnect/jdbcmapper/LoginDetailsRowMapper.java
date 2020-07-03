package com.drrf.alumniconnect.jdbcmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drrf.alumniconnect.model.LoginDetails;

public class LoginDetailsRowMapper implements RowMapper<LoginDetails> {

	@Override
	public LoginDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		 LoginDetails loginDetails = new LoginDetails();
		 loginDetails.setSrNo(resultSet.getLong("SR_NO"));
		 loginDetails.setEmailId(resultSet.getString("EMAIL_ID"));
		 loginDetails.setPassword(resultSet.getString("password"));
	     
		 return loginDetails;
	}

	
}

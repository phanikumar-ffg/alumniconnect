package com.drrf.alumniconnect.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.model.UserDetails;

@Repository
public class LoginDaoImpl implements LoginDao{
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails getUserDetails(UserDetails user) {
		System.out.println(user.toString());
		return jdbcTemplate.queryForObject("select * from user where email = ?", new Object[]{user.getEmail()},
                new BeanPropertyRowMapper<UserDetails>(UserDetails.class));
		//new UserDetails();
	}

}

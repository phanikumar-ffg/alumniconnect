package com.drrf.alumniconnect.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.exceptions.UserNotFoundDaoException;
import com.drrf.alumniconnect.jdbcmapper.LoginDetailsRowMapper;
import com.drrf.alumniconnect.jdbcmapper.UserProfileRowMapper;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;

@Repository
public class LoginDaoImpl implements LoginDao{
	private static final Logger logger = LoggerFactory.getLogger(LoginDao.class);
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public UserProfile getUserDetails(LoginDetails user) throws UserNotFoundDaoException{
		LoginDetails loginDetails = null;
		UserProfile userProfile= null;
		String message = "";

		try {
			String sql_authentication = "SELECT student_id,username,password,create_timestamp,update_timestamp FROM tbl_login_details where username = ? and password= ?";

			loginDetails = jdbcTemplate.queryForObject(sql_authentication, new Object[]{user.getUserName(),user.getPassword()}, new LoginDetailsRowMapper());

			if (loginDetails == null ) {
				throw new UserNotFoundDaoException( "User Id or password not valid");
			}else {
				String sql_user_profile = "select * from tbl_profile_data where email_address = ?";
				userProfile = jdbcTemplate.queryForObject(sql_user_profile, new Object[]{loginDetails.getUserName()}, new UserProfileRowMapper());
			}
		} catch (UserNotFoundDaoException | DataAccessException e) {
			throw new UserNotFoundDaoException( "User Id or password not valid");
		}catch(Exception e) {
			logger.error(e.getLocalizedMessage(),e);
			throw new UserNotFoundDaoException( "Error occured while checking the user account information " +  user.getUserName());
		}

		return userProfile;
		
	}

}

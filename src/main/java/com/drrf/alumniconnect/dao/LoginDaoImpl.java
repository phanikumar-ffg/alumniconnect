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
	private static final Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public UserProfile getUserDetails(LoginDetails user) throws UserNotFoundDaoException{
		LoginDetails loginDetails = null;
		UserProfile userProfile= null;
		logger.info("Validating the user Information:"+user.getEmailId());
		try {
			String sqlAuthentication = "SELECT sr_no ,email_id ,password,create_timestamp,update_timestamp FROM tbl_login_details where email_id = ? and password= ?";

			loginDetails = jdbcTemplate.queryForObject(sqlAuthentication, new Object[]{user.getEmailId(),user.getPassword()}, new LoginDetailsRowMapper());

			if (loginDetails == null ) {
				logger.info("User information not available in login details table:"+user.getEmailId());
				throw new UserNotFoundDaoException( "User Id or password not valid");
			}else {
				String sqlUserProfile = "select pd.aspirant_id, pd.first_name, pd.last_name, pd.email_id, pd.phone, pd.dob, pd.current_organization, pd.city_id,ctd.city_name, pd.centre_id, cd.centre_name, pd.is_admin "
						+ "from alumniconnect.tbl_profile_data pd inner join tbl_centre_details cd on (pd.centre_id= cd.centre_id) inner join tbl_city_details ctd on (pd.city_id = ctd.city_id) where pd.email_id=?";
				userProfile = jdbcTemplate.queryForObject(sqlUserProfile, new Object[]{loginDetails.getEmailId()}, new UserProfileRowMapper());
			}
		} catch (UserNotFoundDaoException | DataAccessException e) {
			throw new UserNotFoundDaoException( "User Id or password not valid");
		}catch(Exception e) {
			logger.error("An exception was thrown in login validation DAO",e);
			throw new UserNotFoundDaoException( "Error occured while checking the user account information " +  user.getEmailId());
		}

		return userProfile;
		
	}

}

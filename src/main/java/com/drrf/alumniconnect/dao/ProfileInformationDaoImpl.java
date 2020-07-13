package com.drrf.alumniconnect.dao;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import com.drrf.alumniconnect.jdbcmapper.UserProfileRowMapper;
import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.exceptions.UserProfileInformationDaoException;


@Repository
public class ProfileInformationDaoImpl implements ProfileInformationDao {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInformationDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserProfile getProfileInfo(String input) {
	UserProfile userProfile = null;

        try {
            // final String get_all_profiles = "SELECT * FROM tbl_profile_data";
            // userProfile = jdbcTemplate.query(get_all_profiles, new UserProfileRowMapper());

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw e;
		}

		return userProfile;
	}

	@Override
	public String updateProfileInfo(UserProfile userProfile){
		return "Success";
	}

	@Override
	public UserProfile saveProfileDetails(UserProfile userProfile) throws UserProfileInformationDaoException{
	try {
			// String sql = "INSERT INTO tbl_profile_data (studentId,firstName,lastName,mobile,email,address,cityId,centerId,dob,createDate,updateDate) VALUES ('" 
			// + userProfile.getStudentId() + "','" + userProfile.getFirstName() +"','" + userProfile.getLastName() + "','" + jobInformation.getMobile() + "',	'" 
			// + userProfile.getEmail() + "','" + userProfile.getAddress() + "','"+ userProfile.getCityId() +"','"+ userProfile.getCenterId() +"','"
			// + userProfile.getDob() +"','"+ userProfile.getCreateDate() +"','"+ userProfile.getUpdateDate() +"')";
			// int i = jdbcTemplate.update(sql);
			int i = 1;
			if(i==0){
				throw new UserProfileInformationDaoException("Error occurred while saving Profile Info");
			}
			else {
				return userProfile;
			}
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new UserProfileInformationDaoException( "Error occured while saving Profile Info");
		}
	}

	@Override
	public String updatePassword(LoginDetails newCredentials) throws UserProfileInformationDaoException{
	try {
		System.out.print(newCredentials);
		java.util.Date date=new java.util.Date();
		Timestamp sqlTime=new Timestamp(date.getTime());
		String sql = "UPDATE tbl_login_details set password = ?, update_timestamp = ? where email_id = ?";
		jdbcTemplate.update(sql, new Object[] { newCredentials.getPassword(), sqlTime, newCredentials.getEmailId()});
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new UserProfileInformationDaoException( "Error occured while updating password");
		}
		return "success";
	}

}

package com.drrf.alumniconnect.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.drrf.alumniconnect.jdbcmapper.UserProfileRowMapper;

@Repository
public class ProfileInformationDaoImpl implements ProfileInformationDao {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInformationDaoImpl.class);

	@Override
	public UserProfile getProfileInfo(String input) {
	UserProfile userProfile = null;

        try {
			// TODO Change table name
            final String get_all_profiles = "SELECT * FROM tbl_profile_data";
            userProfile = jdbcTemplate.query(get_all_profiles, new UserProfileRowMapper());

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw e;
		}

		return userProfile;
	}

	@Override
	public UserProfile updateProfileInfo(UserProfile userProfile){
		// TODO - How to save the data to DB ?
	}

	@Override
	public UserProfile saveProfileDetails(UserProfile userProfile){
	try {
		// TODO- change table Name and column names
			String sql = "INSERT INTO tbl_profile_data (studentId,firstName,lastName,mobile,email,address,cityId,centerId,dob,createDate,updateDate) 
			VALUES ('" + userProfile.getStudentId() + "','" + userProfile.getFirstName() +"','" + userProfile.getLastName() + "','" + jobInformation.getMobile() + "',
			'" + userProfile.getEmail() + "','" + userProfile.getAddress() + "','"+ userProfile.getCityId() +"','"+ userProfile.getCenterId() +"',
			'"+ userProfile.getDob() +"','"+ userProfile.getCreateDate() +"','"+ userProfile.getUpdateDate() +"')";
			int i = jdbcTemplate.update(sql);
			if(i==0){
				throw new UserProfileInformationDaoException("Error occurred while saving Profile Info"+userProfile.getStudentId());
			}
			else {
				return "success";
			}
		}
		catch (UserProfileInformationDaoException e) {
			throw e;
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new UserProfileInformationDaoException( "Error occured while saving Profile Info" +userProfile.getStudentId());
		}
	}

}

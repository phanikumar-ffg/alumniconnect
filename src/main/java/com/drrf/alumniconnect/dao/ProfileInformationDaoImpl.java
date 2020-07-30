package com.drrf.alumniconnect.dao;

import java.sql.Timestamp;
import java.util.HashMap;

import com.drrf.alumniconnect.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import com.drrf.alumniconnect.jdbcmapper.UserProfileRowMapper;
import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.exceptions.UserProfileInformationDaoException;
import org.springframework.web.client.RestTemplate;


@Repository
public class ProfileInformationDaoImpl implements ProfileInformationDao {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInformationDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RestTemplate restTemplate;

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
	public String requestCertificate(CertificateRequestObject certificateRequestObject) throws UserProfileInformationDaoException{
		String key = "o98QEu_kHax";
		ObjectMapper objectMapper= new ObjectMapper();
		String message=null;
		String url = "http://52.172.212.215:93/api/GrowAPI/RequestCerificate?APIKey="+key;
		logger.info(" " + url);
		String response;
		try {
			response =restTemplate.postForObject( url, certificateRequestObject, String.class);
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new UserProfileInformationDaoException( "Error occured while sending Cerificate request");
		}
		try {
			HashMap responseObject=objectMapper.readValue(response, HashMap.class);
			message= (String) responseObject.get("Message");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("Certificate Response "+response);
		return response;
	}

	@Override
	public UserProfile saveProfileDetails(UserProfile userProfile) throws UserProfileInformationDaoException{
		try {
			java.util.Date date=new java.util.Date();
			Timestamp sqlTime=new Timestamp(date.getTime());
			String sql = "UPDATE tbl_profile_data set phone = ?, current_Organization =  ?, city_id = ?, update_timestamp = ? where email_id = ?";
			jdbcTemplate.update(sql, new Object[] { userProfile.getPhone(), userProfile.getCurrentOrganization(), userProfile.getCityId(), sqlTime, userProfile.getEmailId()});
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage(),e);
			throw new UserProfileInformationDaoException( "Error occured while saving Profile Info");
		}
		return userProfile;
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

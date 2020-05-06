package com.drrf.alumniconnect.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.Mail;
import com.drrf.alumniconnect.service.MailService;
import com.drrf.alumniconnect.utils.APIUtils;
 
@Repository
public class ProfileInformationDaoImpl implements ProfileInformationDao {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInformationDaoImpl.class);

	@Override
	public UserProfile getProfileInfo(String input) {
	UserProfile userProfile = null;

        try {
			// TODO Change table name
            final String get_all_profiles = "SELECT * FROM tbl_profile_information";

            userProfile = jdbcTemplate.query(get_all_profiles, new ProfileInformationRowMapper());

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

}

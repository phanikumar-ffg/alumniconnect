package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.exceptions.UserNotFoundDaoException;
import com.drrf.alumniconnect.jdbcmapper.ContentManagementRowMapper;
import com.drrf.alumniconnect.jdbcmapper.LoginDetailsRowMapper;
import com.drrf.alumniconnect.jdbcmapper.UserProfileRowMapper;
import com.drrf.alumniconnect.model.ContentManagement;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DeleteContentDaoImpl implements DeleteContentDao{
    private static final Logger logger = LoggerFactory.getLogger(DeleteContentDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String deleteContentRequest(ContentManagement contentManagement) throws ContentNotFoundDaoException {
        ContentManagement content = new ContentManagement();

        try {
            if ( countRecords(contentManagement) == 1) {
                String contentId = "SELECT * FROM TBL_CONTENT_MANAGEMENT where CONTENT_TITLE = ? ";
                content = jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentTitle()}, new ContentManagementRowMapper());
            } else {
                String contentId = "SELECT * FROM TBL_CONTENT_MANAGEMENT where CONTENT_TITLE = ? and CONTENT_URL = ?";
                content = jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentTitle(),contentManagement.getContentURL()}, new ContentManagementRowMapper());
            }

            if (content == null || content.toString().isEmpty()) {
                throw new ContentNotFoundDaoException( "No Content is found in the Database with selected title: " + contentManagement.getContentTitle());

            }else {
                String sql_delete_content = "DELETE FROM TBL_CONTENT_MANAGEMENT WHERE CONTENT_ID = ?";
                int i = jdbcTemplate.update(sql_delete_content, new Object[]{content.getContentId()});
                if (i == 0) {
                    throw new ContentNotFoundDaoException("Error occurred while deleting Content information: " + contentManagement.getContentTitle());
                } else {
                    logger.info("Request deleted to database successfully");
                    return "Request deleted to database successfully";
                }
            }
        } catch (ContentNotFoundDaoException e) {
            throw e;
        }catch(Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw new ContentNotFoundDaoException( "Error occurred while finding the content Management information" +  contentManagement.getContentTitle());
        }
    }
    public Integer countRecords(ContentManagement contentManagement){
        String contentId = "SELECT(COUNT(*)) FROM TBL_CONTENT_MANAGEMENT where CONTENT_TITLE = ? ";
        return jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentTitle()}, Integer.class);
    }

}
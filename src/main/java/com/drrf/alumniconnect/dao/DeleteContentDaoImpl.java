package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.jdbcmapper.ContentManagementRowMapper;
import com.drrf.alumniconnect.model.ContentManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


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
                String contentId = "SELECT * FROM TBL_CONTENT_MANAGEMENT where CONTENT_DESC = ? ";
                content = jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentDesc()}, new ContentManagementRowMapper());
            } else {
                String contentId = "SELECT * FROM TBL_CONTENT_MANAGEMENT where CONTENT_DESC = ? and CONTENT_URL = ?";
                content = jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentDesc(),contentManagement.getContentURL()}, new ContentManagementRowMapper());
            }

            if (content == null || content.toString().isEmpty()) {
                throw new ContentNotFoundDaoException( "No Content is found in the Database with selected title: " + contentManagement.getContentDesc());

            }else {
                String sqlDeleteContent = "DELETE FROM TBL_CONTENT_MANAGEMENT WHERE CONTENT_ID = ?";
                int i = jdbcTemplate.update(sqlDeleteContent, new Object[]{content.getContentId()});
                if (i == 0) {
                    throw new ContentNotFoundDaoException("Error occurred while deleting Content information: " + contentManagement.getContentDesc());
                } else {
                    logger.info("Request deleted from database successfully");
                    return "Request deleted from database successfully";
                }
            }
        } catch (ContentNotFoundDaoException e) {
            throw e;
        }catch(Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw new ContentNotFoundDaoException( "Error occurred while finding the content Management information: " +  contentManagement.getContentDesc());
        }
    }
    public Integer countRecords(ContentManagement contentManagement){
        String contentId = "SELECT(COUNT(*)) FROM TBL_CONTENT_MANAGEMENT where CONTENT_DESC = ? ";
        return jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentDesc()}, Integer.class);
    }

}

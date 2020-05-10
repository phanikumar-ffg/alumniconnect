package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.jdbcmapper.ContentManagementRowMapper;
import com.drrf.alumniconnect.model.ContentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentManagementDaoImpl implements ContentManagementDao {
    private static final Logger logger = LoggerFactory.getLogger(ContentManagementDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ContentManagement> getAllContentInformation() throws CannotGetJdbcConnectionException {
        List<ContentManagement> contentManagementList = null;

        try {
            final String get_all_content_info = "SELECT * FROM TBL_CONTENT_MANAGEMENT";

            contentManagementList = jdbcTemplate.query(get_all_content_info, new ContentManagementRowMapper());

        } catch (CannotGetJdbcConnectionException e) {
            logger.error("Error occurred while fetching the information from content management table: ",e.getMessage());
        }

        return contentManagementList;

    }
}

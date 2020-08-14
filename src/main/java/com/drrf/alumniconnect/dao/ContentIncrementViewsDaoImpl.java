package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContentIncrementViewsDaoImpl implements ContentIncrementViewsDao {
    private static final Logger logger = LoggerFactory.getLogger(DeleteContentDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public String incrementContentViewsWithContentId(ContentManagement contentManagement) throws ContentNotFoundDaoException {
        ContentManagement content = new ContentManagement();

        try {
            String sqlIncrementContentViews = "UPDATE TBL_CONTENT_MANAGEMENT SET CONTENT_VIEWS = (CONTENT_VIEWS+1) WHERE CONTENT_ID = ?;";
            int i = jdbcTemplate.update(sqlIncrementContentViews, new Object[]{contentManagement.getContentId()});
            if (i == 0) {
                throw new ContentNotFoundDaoException("Error occurred while incrementing : ");
            } else {
                logger.info("Incremented number of views successfully");
                return "Success";
            }

        } catch (ContentNotFoundDaoException e) {
            throw e;
        }catch(Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            throw new ContentNotFoundDaoException( "Error occurred while finding the content Management information: ");
        }
    }
}

package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;

@Repository
public class ContentRequestDaoImpl implements ContentRequestDao {
    private static final Logger logger = LoggerFactory.getLogger(ContentRequestDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

    @Override
    public String sendContentRequest(ContentManagement contentManagement) throws CannotGetJdbcConnectionException {
        try {
            if ((!(contentManagement.getContentURL().isEmpty() || contentManagement.getContentDesc().isEmpty()) && checkURL(contentManagement.getContentURL()))){
                if( countRecords(contentManagement) == 0 ){
                    logger.info("Inserting a new content with Content Title {} and URl {} at time {}", contentManagement.getContentDesc(), contentManagement.getContentURL(), timestamp);
                    contentManagement.setCreateDate(timestamp);
                    String sql = "INSERT INTO TBL_CONTENT_MANAGEMENT (CONTENT_URL,CONTENT_TYPE,CONTENT_DESC,ASSESSMENT_URL, CREATE_TIMESTAMP) VALUES ('" + contentManagement.getContentURL() + "','"
                            + contentManagement.getContentType() + "','" + contentManagement.getContentDesc() + "','" + contentManagement.getAssessmentURL() + "','" + contentManagement.getCreateDate() + "')";
                    int i = jdbcTemplate.update(sql);

                    logger.info("The value of i"+i);
                    if (i == 0) {
                        throw new ContentNotFoundDaoException("Error occurred while saving Content information: " + contentManagement.getContentDesc());
                    } else {
                        return "Request saved to database successfully";
                    }
                }
                else{
                    logger.info( "Failed: Record Found in Database with same URL and Content description");
                    return "Failed: Record Found in Database with same URL and Content description";
                }

            } else {
                if((contentManagement.getContentURL().isEmpty()  || contentManagement.getContentDesc().isEmpty()))
                    return "Error occurred: Cannot save the content with no Title or URL";
                else if (!(checkURL(contentManagement.getContentURL())||checkURL(contentManagement.getAssessmentURL())))
                    return "Error occurred: URL is invalid, Please check";
                else
                    return "Error Occurred";
            }
        } catch (CannotGetJdbcConnectionException | ContentNotFoundDaoException e) {
            logger.error("Error occurred while saving the information to content management table: ", e.getMessage());
            return "Error occurred while saving the information to content management table ";
        }
    }
    public Boolean checkURL(String urlval){
        try{
            URL url = new URL(urlval);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            return (responseCode == 200) ? true : false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Integer countRecords(ContentManagement contentManagement){
        String contentId = "SELECT(COUNT(*)) FROM TBL_CONTENT_MANAGEMENT where CONTENT_DESC = ? AND CONTENT_URL = ?";
        return jdbcTemplate.queryForObject(contentId, new Object[]{contentManagement.getContentDesc(), contentManagement.getContentURL()}, Integer.class);
    }

}


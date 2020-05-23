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
    private static final Logger logger = LoggerFactory.getLogger(ContentManagementDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

    @Override
    public String sendContentRequest(ContentManagement contentManagement) throws CannotGetJdbcConnectionException {
        try {
            if ((!(contentManagement.getContentURL().isEmpty() || contentManagement.getContentTitle().isEmpty()) && checkURL(contentManagement.getContentURL()))){
                logger.info("Inserting a new content with Content Title {} and URl {} at time {}", contentManagement.getContentTitle(), contentManagement.getContentURL(), timestamp);
                contentManagement.setCreateDate(timestamp);
                String sql = "INSERT INTO TBL_CONTENT_MANAGEMENT (CONTENT_ID,CONTENT_URL,CONTENT_TITLE,CONTENT_DESC,CREATE_TIMESTAMP) VALUES ('" + contentManagement.getContentId()
                        + "','" + contentManagement.getContentURL() + "','" + contentManagement.getContentTitle() + "','" + contentManagement.getContentDesc() + "','" + contentManagement.getCreateDate() + "')";
                int i = jdbcTemplate.update(sql);

                System.out.println("The value of i");
                if (i == 0) {
                    throw new ContentNotFoundDaoException("Error occurred while saving Content information: " + contentManagement.getContentTitle());
                } else {
                    return "Request saved to database successfully";
                }
            } else {
                if((contentManagement.getContentURL().isEmpty()  || contentManagement.getContentTitle().isEmpty()))
                    return "Error occurred: Cannot save the content with no Title or URL";
                else if (!checkURL(contentManagement.getContentURL()))
                    return "Error occurred: URL is invalid, Please check";
                else
                    return "Error Occurred";
            }
        } catch (CannotGetJdbcConnectionException | ContentNotFoundDaoException e) {
            logger.error("Error occurred while saving the information to content management table: ", e.getMessage());
            return "Error occurred while saving the information to content management table ";
        }
    }
    public Boolean checkURL(String Url){
        try{
            URL url = new URL(Url);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            return (responseCode == 200) ? true : false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}


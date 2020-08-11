package com.drrf.alumniconnect.dao;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class ContentRequestDaoImpl implements ContentRequestDao {
    private static final Logger logger = LoggerFactory.getLogger(ContentRequestDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SaveUserTokenDaoImpl saveUserTokenDao;

    private Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());

    @Override
    public String sendContentRequest(ContentManagement contentManagement) throws CannotGetJdbcConnectionException {
        List<String> userTokenList = null;
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

                        String title = "New Content Added!!!";
                        String body = "Click to learn more on " + contentManagement.getContentDesc();
                        userTokenList = saveUserTokenDao.getAllUserToken();
                        logger.info( "Started: Push Notifications with Title {} and Body {} for {} clients .",title,body,userTokenList.size());
                        userTokenList.forEach((e) ->{
                            sendPushNotification(e,title,body);
                        });
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
    public void sendPushNotification(String token, String title, String body){
        try{
            logger.info("Sending Push Notification!!!");
            List<MediaType> acceptheader = new ArrayList<>();
            acceptheader.add(MediaType.APPLICATION_JSON);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(acceptheader);
            headers.setHost(new InetSocketAddress("exp.host", 80));
            headers.add(headers.ACCEPT_ENCODING,"gzip");
            headers.add(headers.ACCEPT_ENCODING,"deflate");

            Map<String,Object> map = new HashMap<>();
            map.put("to",token);
            map.put("title",title);
            map.put("body",body);
            map.put("sound","default");

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("https://exp.host/--/api/v2/push/send", entity, String.class);
            if (response.getStatusCode().value() == 200) {
                System.out.println("Push Notification Send Successful");
                System.out.println(response.getBody());
            } else {
                System.out.println("Failure: Sending Push Notifications");
                System.out.println(response.getStatusCode());
            }

        } catch(Exception e){
            logger.error("Error occurred in sending PushNotifications because: "+ e.getMessage(),e);
        }
    }
}


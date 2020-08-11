package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.exceptions.ContentNotFoundDaoException;
import com.drrf.alumniconnect.model.ContentManagement;
import com.drrf.alumniconnect.model.UserDetailsForNotification;
import com.drrf.alumniconnect.service.SaveUserTokenService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class SendNotificationController {
    private static Logger logger = LoggerFactory.getLogger(SendNotificationController.class.getName());

    @Autowired
    private SaveUserTokenService saveUserTokenService;

    @POST
    @Path("/saveUserToken")
    public Response contentRequest(@RequestBody String userToken) {
        logger.info("Received a user Token. Let's validate if we have this in database or not");
        try {
            String responseMessage = saveUserTokenService.saveUserToken(userToken);
            if("Request saved to database successfully".equalsIgnoreCase(responseMessage)) {
                return Response.ok().entity(responseMessage).build();
            }
            else
                throw new ContentNotFoundDaoException(responseMessage);
        } catch (Exception e) {
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.toString()).build();
        }
    }
}

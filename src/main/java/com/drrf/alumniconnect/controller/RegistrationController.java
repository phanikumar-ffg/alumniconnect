package com.drrf.alumniconnect.controller;


import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.service.RegistrationService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private RegistrationService registrationService;

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userRegistration(@RequestBody UserProfile userProfile) {
        logger.info("Received request for new User signup");
        try {
            return Response.ok().entity(registrationService.newUserRegistration(userProfile)).build();
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }

    @GET
    @Path("/centreDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCentres(){
        try {
            return Response.ok().entity(registrationService.getCentres()).build();
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }
}

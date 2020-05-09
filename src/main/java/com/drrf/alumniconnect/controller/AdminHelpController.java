package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.service.AdminHelpService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.AuthenticationFailedException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/v1")
public class AdminHelpController {
    private static final Logger logger = LoggerFactory.getLogger(AdminHelpController.class);
    @Autowired
    AdminHelpService adminHelpService;

    @GET
    @Path("/adminhelp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminHelpRequests() {
        try {
            return Response.ok().entity(adminHelpService.getAllHelpRequests().toString()).build();
        } catch (ForgotPasswordDaoException e) {
            JsonObject error = new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            //return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        } catch (AuthenticationFailedException e) {
            JsonObject error = new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            //return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }

    }
}

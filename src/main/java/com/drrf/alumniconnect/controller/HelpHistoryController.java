package com.drrf.alumniconnect.controller;


import com.drrf.alumniconnect.exceptions.HelpHistoryDaoException;
import com.drrf.alumniconnect.model.HelpHistory;
import com.drrf.alumniconnect.service.HelpHistoryService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/api/v1")
public class HelpHistoryController {
    private static final Logger logger = LoggerFactory.getLogger(HelpHistoryController.class);
    @Autowired
    private HelpHistoryService helpHistoryService;

    @POST
    @Path("/help")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveHelpHistory(@RequestBody HelpHistory helpHistory){
        logger.info("Saving Help History Data");
        try {
            return Response.ok().entity(helpHistoryService.saveHelpDetails(helpHistory)).build();
        }
        catch (HelpHistoryDaoException e){
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }

    @GET
    @Path("/helpDetails/{helpType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelpDetails(@PathParam(value = "helpType")String helpType){
        logger.info("Received request for get help details information");

        try {
            return Response.ok().entity(helpHistoryService.getHelpDetails(helpType)).build();
        } catch (HelpHistoryDaoException e) {
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }
}

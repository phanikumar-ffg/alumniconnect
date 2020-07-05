package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.service.CityDetailsService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class CityDetailsController {
    private static final Logger logger = LoggerFactory.getLogger(CityDetailsController.class);
    @Autowired
    private CityDetailsService cityDetailsService;

    @GET
    @Path("/stateDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStateDetails(){
        logger.info("Received request for fetching state details");
        try {
            return Response.ok().entity(cityDetailsService.getStateDetails()).build();
        } catch (CityDetailsDaoException e) {
            logger.info("Bad request");
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }

    @GET
    @Path("/cityDetails/{state_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityDetails(@PathParam(value ="state_id")Long state_id){
        logger.info("Received request for fetching city details for that state");
        try {
            if(state_id == null) {
                logger.error("Error occured in fetching corresponding state value");
            }
                return Response.ok().entity(cityDetailsService.getCityDetails(state_id)).build();
        } catch (CityDetailsDaoException e) {
            logger.info("Bad request");
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }
}

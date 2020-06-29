package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.exceptions.CityDetailsDaoException;
import com.drrf.alumniconnect.service.CityDetailsService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class CityDetailsController {
    @Autowired
    private CityDetailsService cityDetailsService;

    @GET
    @Path("/cityDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHelpDetails(){
        try {
            return Response.ok().entity(cityDetailsService.getCityDetails()).build();
        } catch (CityDetailsDaoException e) {
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            //return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }
}

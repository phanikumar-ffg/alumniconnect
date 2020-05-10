package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.service.ContentManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class ContentManagementController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ContentManagementService contentManagementService;

    @GET
    @Path("/content/details")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getContentManagementDetails() {
        logger.info("Request to get all the information for Content Management");
        try {
            return Response.ok().entity(contentManagementService.getAllContentInformation()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("error").build();
        }


    }


    /*@POST
    @Path("/content/request")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response contentRequest(@RequestBody ContentManagement content) {
		logger.info("Received a content Management request");
		try {
			return Response.ok().entity(ContentRequestService.sendContentRequest(content)).build();
		} catch (Exception e) {
			JsonObject error=new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			//return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			return Response.status(Status.BAD_REQUEST).entity(error.toString()).build();
		}


    }*/
}

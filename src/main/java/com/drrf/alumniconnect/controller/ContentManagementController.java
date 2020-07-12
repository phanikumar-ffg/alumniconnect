package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.model.ContentManagement;
import com.drrf.alumniconnect.service.ContentManagementService;
import com.drrf.alumniconnect.service.ContentRequestService;
import com.drrf.alumniconnect.service.DeleteContentService;
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
public class ContentManagementController {
    private static Logger logger = LoggerFactory.getLogger(ContentManagementController.class.getName());
    @Autowired
    private ContentManagementService contentManagementService;

    @Autowired
    private ContentRequestService contentRequestService;

    @Autowired
    private DeleteContentService deleteContentService;

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



    @POST
    @Path("/content/request")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response contentRequest(@RequestBody ContentManagement content) {
        logger.info("Received a content Management request");
        try {
            String responseMessage = contentRequestService.sendContentRequest(content);
            if(responseMessage == "Request saved to database successfully") {
                return Response.ok().entity(responseMessage).build();
            }
            else
                throw new Exception(responseMessage);
        } catch (Exception e) {
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.toString()).build();
        }
    }
    @POST
    @Path("/content/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response contentDelete(@RequestBody ContentManagement content) {
        logger.info("Deleting content Management request");
        try {
            String responseMessage = deleteContentService.deleteContentRequest (content);
            if(responseMessage == "Request deleted from database successfully") {
                return Response.ok().entity(responseMessage).build();
            }
            else
                throw new Exception(responseMessage);
        } catch (Exception e) {
            JsonObject error=new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.toString()).build();
        }

    }

}

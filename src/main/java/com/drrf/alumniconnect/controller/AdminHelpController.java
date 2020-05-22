package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.service.AdminHelpService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.AuthenticationFailedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api/v1")
public class AdminHelpController {
    private static final Logger logger = LoggerFactory.getLogger(AdminHelpController.class);
    @Autowired
    AdminHelpService adminHelpService;

    @GET
    @Path("/adminhelp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAdminHelpRequests() {
        try {
            return Response.ok().entity(adminHelpService.getAllHelpRequests()).build();
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
        catch(Exception e){
            return Response.status(Response.Status.BAD_REQUEST).type("ERROR GETTING_RESPONSE").build();
        }

    }

    @PUT
    @Path("/update_adminhelp/update_status/{'Student_Id','Status'}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatusOfAdminHelpRequest(@PathParam("Student_Id") long Student_Id, @PathParam("Status") String Status){
        try{
            AdminHelpRequestStatus adminHelpRequestStatus=new AdminHelpRequestStatus();
            adminHelpRequestStatus.setStudentId(Student_Id);
            adminHelpRequestStatus.setStatus(Status);
            return Response.ok().entity(adminHelpService.updateAdminHelpStatus(adminHelpRequestStatus)).build();
        }
        catch (ForgotPasswordDaoException e) {
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

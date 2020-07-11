package com.drrf.alumniconnect.controller;

import com.drrf.alumniconnect.exceptions.AdminHelpRequestDaoException;
import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.model.AdminHelpRequestStatus;
import com.drrf.alumniconnect.service.AdminHelpService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.AuthenticationFailedException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api/v1")
@Controller
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
        } catch (AdminHelpRequestDaoException e) {
            JsonObject error = new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            //return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }

        }



    @PUT
    @Path("/update/{Student_Id}/{Status}")
    public Response updateStatusOfAdminHelpRequest(@PathParam("Student_Id") String Student_Id,@PathParam("Status") String Status){
        try{
            AdminHelpRequestStatus adminHelpRequestStatus=new AdminHelpRequestStatus();
            adminHelpRequestStatus.setAspirantId(Integer.parseInt(Student_Id));
            adminHelpRequestStatus.setStatus(Status);
            return Response.ok().entity(adminHelpService.updateAdminHelpStatus(adminHelpRequestStatus)).build();
        }
        catch (AdminHelpRequestDaoException e) {
            JsonObject error = new JsonObject();
            error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
            //return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }



}

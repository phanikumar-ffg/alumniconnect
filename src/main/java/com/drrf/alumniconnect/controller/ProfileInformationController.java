package com.drrf.alumniconnect.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.drrf.alumniconnect.model.CertificateRequestObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;

import com.drrf.alumniconnect.service.ProfileInformationService;
import com.drrf.alumniconnect.model.UserProfile;
import com.drrf.alumniconnect.model.LoginDetails;


@Path("/api/v1")
public class ProfileInformationController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileInformationController.class);
    @Autowired
    private ProfileInformationService profileInformationService;

    @GET
    @Path("/profile/{input}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProfileInfo(@PathParam(value = "input") String input) {
        logger.info("Received request for Profile");
        try {
            return Response.ok().entity(profileInformationService.getProfileInfo(input)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity("getProfileInfoError").build();
        }

    }

    @POST
    @Path("/requestCertificate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfileInfo(@RequestBody CertificateRequestObject certificateRequestObject) {
        logger.info("Received request for Profile " + certificateRequestObject.getAspirantId() + certificateRequestObject.getEmail() + certificateRequestObject.getCenterId() + certificateRequestObject.getCenterName());
        try {
            return Response.ok().entity(profileInformationService.requestCertificate(certificateRequestObject)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity("requestCertificateError").build();
        }
    }

    @POST
    @Path("/updateProfile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveProfileDetails(@RequestBody UserProfile userProfile) {
        try {
            return Response.ok().entity(profileInformationService.saveProfileDetails(userProfile)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity("updateProfileError").build();
        }
    }

    @POST
    @Path("/updatePassword")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveProfileDetails(@RequestBody LoginDetails newPassword) {
        System.out.print(newPassword.getEmailId()+' '+newPassword.getPassword());
        try {
            return Response.ok().entity(profileInformationService.updatePassword(newPassword)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity("updatePasswordError").build();
        }
    }
}

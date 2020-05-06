package com.drrf.alumniconnect.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;

import com.drrf.alumniconnect.service.ProfileInformationService;
import com.drrf.alumniconnect.model.UserProfile;


@Path("/api/v1")
public class ProfileInformationController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInformationController.class);
	@Autowired
	private ProfileInformationService profileInformationService;
	
	@GET
	@Path("/profile/{input}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProfileInfo(@PathVariable("input") String input) { 
		logger.info("Received request for Profile");
		try {
			return Response.ok().entity(profileInformationService.getProfileInfo(input)).build();
		} catch (Exception e) {	
			return Response.status(Status.BAD_REQUEST).entity("error").build();
		}

	}

	@GET
	@Path("/profileUpdate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProfileInfo(@RequestBody UserProfile userProfile) { 
		logger.info("Received request for Profile");
		try {
			return Response.ok().entity(profileInformationService.updateProfileInfo(userProfile)).build();
		} catch (Exception e) {	
			return Response.status(Status.BAD_REQUEST).entity("error").build();
		}

	}
}

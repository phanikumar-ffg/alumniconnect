package com.drrf.alumniconnect.controller;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.drrf.alumniconnect.exceptions.ForgotPasswordDaoException;
import com.drrf.alumniconnect.service.ForgotPasswordService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;


@Path("/api/v1")
public class ForgotPasswordController {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@GET
	@Path("/forgotpassword/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getForgotPassword(@PathParam(value = "email") String email) {
		logger.info("Received request for get password information");
		
		try {
			//responseObj.put("", forgotPasswordService.getForgotPasswordInformation(email));
			return Response.ok().entity(forgotPasswordService.getForgotPasswordInformation(email)).build();
		} catch (ForgotPasswordDaoException e) {	
			JsonObject error=new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			return Response.status(Status.BAD_REQUEST).entity(error.toString()).build();
		}catch (AuthenticationFailedException e) {	
			JsonObject error=new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			return Response.status(Status.BAD_REQUEST).entity(error.toString()).build();
		}


	}
	
}

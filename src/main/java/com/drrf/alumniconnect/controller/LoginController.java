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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.drrf.alumniconnect.exceptions.UserNotFoundDaoException;
import com.drrf.alumniconnect.model.LoginDetails;
import com.drrf.alumniconnect.service.LoginService;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;


@Path("/api/v1")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;

	@GET
	@Path("/ping")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApplicationRunStatus() {
		logger.info("Received request for api status");
		JsonObject response=new JsonObject();
		try {
			response.addProperty("api", "Dr Reddy Alumni Connect API");
			response.addProperty("status", "Up and Running");
			return Response.ok().entity(response.toString()).build();
		} catch (Exception e) {
			response.addProperty("api", "Dr Reddy Alumni Connect API");
			response.addProperty("status", "Error Not Running.");
			return Response.serverError().entity(response.toString()).build();
		}

	}

	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userLogin(@RequestBody LoginDetails user) { 
		logger.info("Received request for login");
		try {
			return Response.ok().entity(loginService.getUserDetails(user)).build();
		} catch (UserNotFoundDaoException e) {	
			JsonObject error=new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			return Response.status(Status.BAD_REQUEST).entity(error.toString()).build();
		}

	}
}

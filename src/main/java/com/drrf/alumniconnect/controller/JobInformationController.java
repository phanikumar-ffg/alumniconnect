package com.drrf.alumniconnect.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.drrf.alumniconnect.exceptions.JobInformationDaoException;
import com.drrf.alumniconnect.model.JobInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.drrf.alumniconnect.utils.APIUtils;
import com.google.gson.JsonObject;

import com.drrf.alumniconnect.service.JobInformationService;
import com.drrf.alumniconnect.service.JobRequestService;
import com.drrf.alumniconnect.model.JobRequest;


@Path("/api/v1")
public class JobInformationController {
	private static final Logger logger = LoggerFactory.getLogger(JobInformationController.class);
	@Autowired
	private JobInformationService JobInfoService;

	@Autowired
	private JobRequestService JobReqService;
	
	@GET
	@Path("/jobs/{student_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getJobsForStudent(@PathParam(value = "student_id")Long studentId) {
		logger.info(String.format("Received request for Jobs by aspirant: {0} ", studentId));
		try {
			return Response.ok().entity(JobInfoService.getJobs(studentId)).build();
		} catch (Exception e) {	
			logger.error(String.format("Error: {0}",e.getLocalizedMessage()));
			return Response.status(Status.BAD_REQUEST).entity("error in retrieving jobs").build();
		}

	}

	@GET
	@Path("/alljobs")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllJobs() {
		logger.info("Received request for all Jobs");
		try {
			return Response.ok().entity(JobInfoService.getAllJobs()).build();
		} catch (Exception e) {	
			logger.error("Error: "+e.getLocalizedMessage());
			return Response.status(Status.BAD_REQUEST).entity("error in retrieving jobs").build();
		}

	}

	@POST
	@Path("/jobrequest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response jobRequest(@RequestBody JobRequest job) { 
		logger.info(String.format("Received a job application request: {0}", job));
		try {
			String resMessage = JobReqService.sendJobRequest(job);
			if (resMessage.equals("Sending Job Request Failed") ){
				throw new Exception(resMessage);
			}
			return Response.ok().entity(resMessage).build();
		} catch (Exception e) {	
			JsonObject error=new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error.toString()).build();
		}

	}


	@POST
	@Path("/saveJobInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveJobEntryDetails(@RequestBody JobInformation jobInformation) throws JobInformationDaoException {
		try {
			return Response.ok().entity(JobInfoService.saveJobEntryDetails(jobInformation)).build();
		} catch (JobInformationDaoException e) {
			JsonObject error = new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
		}
	}

	@POST
	@Path("/deleteJobInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteJobEntry(@RequestBody JobRequest jobInfo) { 
		logger.info(String.format("Received a delete request Job_ID: " , jobInfo.getJobId()));
		try {
			return Response.ok().entity(JobInfoService.deleteJobEntry(jobInfo)).build();
		} catch (JobInformationDaoException e) {
			JsonObject error = new JsonObject();
			error.addProperty(APIUtils.ERROR_MESSAGE, e.getLocalizedMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
		}

	}
}

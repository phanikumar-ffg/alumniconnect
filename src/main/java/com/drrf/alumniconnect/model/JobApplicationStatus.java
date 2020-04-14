package com.drrf.alumniconnect.model;

import java.sql.Date;
public class JobApplicationStatus {

	private long studentId;
	private long jobId;
	private String applicationStatus;
	private Date selectionDate;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public Date getSelectionDate() {
		return selectionDate;
	}
	public void setSelectionDate(Date selectionDate) {
		this.selectionDate = selectionDate;
	}
	
	
}

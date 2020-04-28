package com.drrf.alumniconnect.model;

public class JobRequest {
    private long studentId;
    private String studentName;
    private long jobId;
    private String jobRole;
    private String jobCompanyName;
    private String jobCity;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
    }
    public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
    }
    public String getJobCompanyName() {
		return jobCompanyName;
	}
	public void setJobCompanyName(String jobCompany) {
		this.jobCompanyName = jobCompany;
    }
    public String getJobCity() {
		return jobCity;
	}
	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}
    public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

}
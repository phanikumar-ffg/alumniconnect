package com.drrf.alumniconnect.model;
public class JobInformation {

	private long jobId;
	private String companyName;
	private String role;
	private String jobDescription;
	private String city;
	private String state;
	private String salaryScale;
	
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSalaryScale() {
		return salaryScale;
	}
	public void setSalaryScale(String salaryScale) {
		this.salaryScale = salaryScale;
	}
	
	
}

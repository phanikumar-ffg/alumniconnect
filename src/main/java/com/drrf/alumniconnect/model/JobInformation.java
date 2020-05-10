package com.drrf.alumniconnect.model;

import java.sql.Timestamp;

public class JobInformation {

	private long JOB_ID;
	private String COMPANY_NAME;
	private String JOB_DESCRIPTION;
	private long CITY_ID;
	private long VACANCY_COUNT;
	private String DESIGNATION;
	private String QUALIFICATION_REQ;
	private Timestamp CREATE_TIMESTAMP;
	private String CITY;

	
	public long getJobId() {
		return JOB_ID;
	}
	public void setJobId(long jobId) {
		this.JOB_ID = jobId;
	}
	public String getCompanyName() {
		return COMPANY_NAME;
	}
	public void setCompanyName(String companyName) {
		this.COMPANY_NAME = companyName;
	}
	public String getJobDescription() {
		return JOB_DESCRIPTION;
	}
	public void setJobDescription(String jobDescription) {
		this.JOB_DESCRIPTION = jobDescription;
	}
	public String getDesignation(){
		return DESIGNATION;
	}
	public void setDesignation(String designation){
		this.DESIGNATION=designation;
	}
	public long getCityId(){
		return CITY_ID;
	}
	public void setCityId(long cityId){
		this.CITY_ID=cityId;
	}
	public long getVacancyCount(){
		return VACANCY_COUNT;
	}
	public void setVacancyCount(long vacancyCount){
		this.VACANCY_COUNT=vacancyCount;
	}
	public String getQualificationReq(){
		return QUALIFICATION_REQ;
	}
	public void setQualificationReq(String qualificationReq){
		this.QUALIFICATION_REQ=qualificationReq;
	}
	public Timestamp getCreateDate() {
		return CREATE_TIMESTAMP;
	}
	public void setCreateDate(Timestamp createDate) {
		this.CREATE_TIMESTAMP = createDate;
	}
	public String getCity(){
		return CITY;
	}
	public void setCity(String city){
		this.CITY=city;
	}

	@Override
	public String toString() {
		return "JobInformation [jobId=" + JOB_ID + ", companyName=" + COMPANY_NAME + ", designation=" + DESIGNATION + ", jobDescription="
				+ JOB_DESCRIPTION + ", cityId=" + CITY_ID + ", vacancyCount=" + VACANCY_COUNT + ",qualificationReq="+ QUALIFICATION_REQ + ",createDate=" + CREATE_TIMESTAMP +"]";
	}
	
}

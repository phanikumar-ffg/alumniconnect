package com.drrf.alumniconnect.model;

import java.sql.Timestamp;

public class JobInformation {

	private long jobId;
	private String companyName;
	private String jobDescription;
	private long cityId;
	private String cityName;
	private long vacancyCount;
	private String designation;
	private String qualificationReq;
	private Timestamp createTimestamp;

	
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
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getdesignation(){
		return designation;
	}
	public void setdesignation(String designation){
		this.designation=designation;
	}
	public long getCityId(){
		return cityId;
	}
	public void setCityId(long cityId){
		this.cityId=cityId;
	}
	public String getCityName(){
		return cityName;
	}
	public void setCityName(String city){
		this.cityName=city;
	}
	public long getVacancyCount(){
		return vacancyCount;
	}
	public void setVacancyCount(long vacancyCount){
		this.vacancyCount=vacancyCount;
	}
	public String getQualificationReq(){
		return qualificationReq;
	}
	public void setQualificationReq(String qualificationReq){
		this.qualificationReq=qualificationReq;
	}
	public Timestamp getCreateDate() {
		return createTimestamp;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createTimestamp = createDate;
	}

	@Override
	public String toString() {
		return "JobInformation [jobId=" + jobId + ", companyName=" + companyName + ", designation=" + designation + ", jobDescription="
				+ jobDescription + ", cityId=" + cityId + ", cityName=" + cityName+", vacancyCount=" + vacancyCount + ",qualificationReq="+ qualificationReq + ",createDate=" + createTimestamp +"]";
	}
	
}

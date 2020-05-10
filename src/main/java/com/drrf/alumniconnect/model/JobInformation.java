package com.drrf.alumniconnect.model;

import java.sql.Timestamp;

public class JobInformation {

	private long jobId;
	private String companyName;
	private String jobDescription;
	private long cityId;
	private long vacancyCount;
	private String designation;
	private String qualificationReq;
	private Timestamp createDate;

	
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
	public String getDesignation(){return designation;}
	public void setDesignation(String designation){this.designation=designation;}
	public long getCityId(){return cityId;}
	public void setCityId(long cityId){this.cityId=cityId;}
	public long getVacancyCount(){return vacancyCount;}
	public void setVacancyCount(long vacancyCount){this.vacancyCount=vacancyCount;}
	public String getQualificationReq(){return qualificationReq;}
	public void setQualificationReq(String qualificationReq){this.qualificationReq=qualificationReq;}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "JobInformation [jobId=" + jobId + ", companyName=" + companyName + ", designation=" + designation + ", jobDescription="
				+ jobDescription + ", cityId=" + cityId + ", vacancyCount=" + vacancyCount + ",qualificationReq="+ qualificationReq + ",createDate=" + createDate +"]";
	}
	
}

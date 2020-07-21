package com.drrf.alumniconnect.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
public class UserProfile {
	
	private long aspirantId;
	private String firstName;
	private String lastName;
	private String emailId;
	private long phone;
	private Date dob;
	private String currentOrganization;
	private long cityId;
	private String cityName;
	private Long stateId;
	private String stateName;
	private long centerId;
	private String centerName;
	private String isAdmin;
	
	private Date createDate;
	private Date updateDate;
	
	public long getAspirantId() {
		return aspirantId;
	}
	public void setAspirantId(long aspirantId) {
		this.aspirantId = aspirantId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCurrentOrganization() {
		return currentOrganization;
	}
	public void setCurrentOrganization(String currentOrganization) {
		this.currentOrganization = currentOrganization;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public long getCenterId() {
		return centerId;
	}
	public void setCenterId(long centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	@JsonIgnore
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@JsonIgnore
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	@Override
	public String toString() {
		return "UserProfile [aspirantId=" + aspirantId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", phone=" + phone + ", dob=" + dob + ", currentOrganization="
				+ currentOrganization + ", cityId=" + cityId + ", cityName=" + cityName + ", stateId=" + stateId
				+ ", stateName=" + stateName + ", centerId=" + centerId + ", centerName=" + centerName + ", isAdmin="
				+ isAdmin + ", createDate=" + createDate + ", updateDate=" + updateDate + ", getAspirantId()="
				+ getAspirantId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getEmailId()=" + getEmailId() + ", getPhone()=" + getPhone() + ", getDob()=" + getDob()
				+ ", getCurrentOrganization()=" + getCurrentOrganization() + ", getCityId()=" + getCityId()
				+ ", getCityName()=" + getCityName() + ", getCenterId()=" + getCenterId() + ", getCenterName()="
				+ getCenterName() + ", getIsAdmin()=" + getIsAdmin() + ", getCreateDate()=" + getCreateDate()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getStateId()=" + getStateId() + ", getStateName()="
				+ getStateName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


	
	
}

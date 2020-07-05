package com.drrf.alumniconnect.model;

import java.sql.Timestamp;
public class LoginDetails {

	private Long srNo;
	private String emailId;
	private String password;
	private Timestamp createDate;
	private Timestamp updateDate;
	


	public Long getSrNo() {
		return srNo;
	}
	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "LoginDetails [srNo=" + srNo + ", emailId=" + emailId + ", password=" + password + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	
	
}

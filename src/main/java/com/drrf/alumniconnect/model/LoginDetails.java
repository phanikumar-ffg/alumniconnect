package com.drrf.alumniconnect.model;

import java.sql.Timestamp;
public class LoginDetails {

	private Long studentId;
	private String userName;
	private String password;
	private Timestamp createDate;
	private Timestamp updateDate;
	

	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		return "LoginDetails [studentId=" + studentId + ", userName=" + userName + ", password=" + password
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
}

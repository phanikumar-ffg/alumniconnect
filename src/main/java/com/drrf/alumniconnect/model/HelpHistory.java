package com.drrf.alumniconnect.model;

import java.sql.Timestamp;
public class HelpHistory {

	private long studentId;
	private String reason;
	private String details;
	private String centerId;
	private Timestamp createDate;
	private String description;
	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	@Override
	public String toString() {
		return "HelpHistory [studentId=" + studentId + ", reason=" + reason + ", details=" + details + ", centerId="
				+ centerId + ", createDate=" + createDate + ", description=" + description + "]";
	}
}

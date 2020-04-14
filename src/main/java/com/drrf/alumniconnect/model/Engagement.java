package com.drrf.alumniconnect.model;

import java.sql.Timestamp;
public class Engagement {

	public String alertId;
	public String alertType;
	public String alertDescription;
	public String activeStatus;
	public Timestamp createDate;
	public Timestamp updateDate;
	
	
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getAlertDescription() {
		return alertDescription;
	}
	public void setAlertDescription(String alertDescription) {
		this.alertDescription = alertDescription;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
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
	
	
}

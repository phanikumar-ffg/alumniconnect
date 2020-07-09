package com.drrf.alumniconnect.model;

import java.sql.Timestamp;

public class AdminHelpRequestStatus {
    private long AspirantId;
    private String reason;
    private String details;
    private String centerId;
    private String description;
    private String status;
    private Timestamp createdTime;

    public long getAspirantId() {
        return AspirantId;
    }

    public void setAspirantId(long aspirantId) {
        AspirantId = aspirantId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}

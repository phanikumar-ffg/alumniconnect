package com.drrf.alumniconnect.model;

import java.sql.Timestamp;

public class AdminHelpRequestStatus {
    private long StudentId;
    private String status;
    private Timestamp createdTime;

    public long getStudentId() {
        return StudentId;
    }

    public void setStudentId(long studentId) {
        StudentId = studentId;
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

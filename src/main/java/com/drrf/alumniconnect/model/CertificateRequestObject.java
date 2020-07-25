package com.drrf.alumniconnect.model;

import java.util.Date;

public class CertificateRequestObject {
    private long aspirantId;
    private String emailId;
    private long centerId;
    private String centerName;

    public long getAspirantId() {
        return aspirantId;
    }

    public void setAspirantId(long aspirantId) {
        this.aspirantId = aspirantId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
}

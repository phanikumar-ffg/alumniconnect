package com.drrf.alumniconnect.model;

import java.util.Date;

public class CertificateRequestObject {
    private long aspirantId;
    private String Email;
    private long centerId;
    private String centerName;

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public long getAspirantId() {
        return this.aspirantId;
    }

    public void setAspirantId(long aspirantId) {
        this.aspirantId = aspirantId;
    }



    public long getCenterId() {
        return this.centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    @Override
    public String toString() {
        return "CertificateRequestObject{" +
                "aspirantId=" + this.aspirantId +
                ", Email='" + this.Email + '\'' +
                ", centerId=" + this.centerId +
                ", centerName='" + this.centerName + '\'' +
                '}';
    }

    public String getCenterName() {
        return this.centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
}
package com.drrf.alumniconnect.model;

import java.util.Date;

public class CertificateRequestObject {
    private long aspirantId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private long certificate_type;
    private long centerId;
    private String centerName;

    public long getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(long certificate_type) {
        this.certificate_type = certificate_type;
    }

    public long getAspirantId() {
        return aspirantId;
    }

    public void setAspirantId(long aspirantId) {
        this.aspirantId = aspirantId;
    }



    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    @Override
    public String toString() {
        return "CertificateRequestObject{" +
                "aspirantId=" + aspirantId +
                ", emailId='" + email + '\'' +
                ", centerId=" + centerId +
                ", centerName='" + centerName + '\'' +
                '}';
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
}

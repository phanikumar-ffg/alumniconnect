package com.drrf.alumniconnect.model;

import java.sql.Timestamp;

public class ContentManagement {

    private long contentId;
    private String contentURL;
    private String contentType;
    private String contentDesc;
    private String assessmentURL;
    private Timestamp createDate;

    public String getAssessmentURL() {
        return assessmentURL;
    }

    public void setAssessmentURL(String assessmentURL) {
        this.assessmentURL = assessmentURL;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}

package com.drrf.alumniconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Timestamp;

public class HelpDetails {
    @Id
    @Column("HELP_ID")
    private long helpId;
    private String helpType;
    private String helpValue;
    private Timestamp createDate;

    public long getHelpId() {
        return helpId;
    }

    public void setHelpId(long helpId) {
        this.helpId = helpId;
    }

    public String getHelpType() {
        return helpType;
    }

    public void setHelpType(String helpType) {
        this.helpType = helpType;
    }

    public String getHelpValue() {
        return helpValue;
    }

    public void setHelpValue(String helpValue) {
        this.helpValue = helpValue;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return " HelpDetails[helpId=" + helpId + ", helpType=" + helpType + ", helpValue=" + helpValue + ",createDate=" + createDate + "]";
    }
}
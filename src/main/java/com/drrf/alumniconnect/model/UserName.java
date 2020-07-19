package com.drrf.alumniconnect.model;

public class UserName {
    private long aspirantId;
    private String firstName;
    private String lastName;
    public long getAspirantId() {
        return aspirantId;
    }
    public void setAspirantId(long aspirantId) {
        this.aspirantId = aspirantId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "UserName [aspirantId=" + aspirantId + ", firstName=" + firstName + ", lastName=" + lastName+ "]";
    }
}

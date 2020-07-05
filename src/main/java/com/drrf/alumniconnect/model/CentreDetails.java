package com.drrf.alumniconnect.model;

public class CentreDetails {
    private long centreId;
    private String centreName;

    public long getCentreId() {
        return centreId;
    }

    public void setCentreId(long centreId) {
        this.centreId = centreId;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    @Override
    public String toString() {
        return "CentreDetails [" +
                "centreId=" + centreId +
                ", centreName='" + centreName +
                ']';
    }
}

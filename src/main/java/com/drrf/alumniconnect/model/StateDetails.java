package com.drrf.alumniconnect.model;

public class StateDetails {

    private long stateId;
    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "StateDetails[stateId="+ stateId +",stateName=" + stateName + "]";
    }
}

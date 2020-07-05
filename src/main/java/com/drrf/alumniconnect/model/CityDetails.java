package com.drrf.alumniconnect.model;
import java.sql.Timestamp;
public class CityDetails {

	private long cityId;
	private String cityName;
	private long stateId;
	
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public long getStateId(){ return stateId;}
	public void setStateId(long stateId){ this.stateId = stateId; }
	
	@Override
	public String toString() {
		return "CityDetails [cityId=" + cityId + ", cityName=" + cityName + ", stateId=" + stateId + "]";
	}
	
	
	
}

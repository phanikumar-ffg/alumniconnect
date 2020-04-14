package com.drrf.alumniconnect.model;
import java.sql.Timestamp;
public class CityDetails {

	private long cityId;
	private String city;
	private String state;
	private Timestamp createDate;
	
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "CityDetails [cityId=" + cityId + ", city=" + city + ", state=" + state + ", createDate=" + createDate
				+ "]";
	}
	
	
	
}

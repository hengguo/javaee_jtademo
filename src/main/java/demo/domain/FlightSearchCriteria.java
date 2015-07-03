package demo.domain;

import java.util.Date;

public class FlightSearchCriteria {
	private String departFrom;
	private String arriveAt;
	private Date departOn;
	private Date arriveOn;
	public String getDepartFrom() {
		return departFrom;
	}
	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}
	public String getArriveAt() {
		return arriveAt;
	}
	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}
	public Date getDepartOn() {
		return departOn;
	}
	public void setDepartOn(Date departOn) {
		this.departOn = departOn;
	}
	public Date getArriveOn() {
		return arriveOn;
	}
	public void setArriveOn(Date arriveOn) {
		this.arriveOn = arriveOn;
	}
}

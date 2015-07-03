package demo.domain;

import java.util.Date;

public class FlightLen {
	private Airport departFrom;
	private Airport arriveAt;
	private Date departOn;
	private Date arriveOn;
	public FlightLen(Airport departFrom, Airport arriveAt, Date departOn, Date arriveOn) {
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.departOn = departOn;
		this.arriveOn = arriveOn;
	}
	public Airport getDepartFrom() {
		return departFrom;
	}
	public Airport getArriveAt() {
		return arriveAt;
	}
	public Date getDepartOn() {
		return departOn;
	}
	public Date getArriveOn() {
		return arriveOn;
	}
}

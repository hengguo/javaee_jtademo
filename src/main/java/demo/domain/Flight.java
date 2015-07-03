package demo.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

public class Flight {
	private List<FlightLen> legs;
	private BigDecimal totalCost;
	public Flight(List<FlightLen> legs, BigDecimal totalCost){
		this.legs = legs;
		this.totalCost = totalCost;
	}
	public List<FlightLen> getLegs() {
		return legs;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	public FlightLen  getFirstLen(){
		return legs.get(0);
	}
	
	public FlightLen  getLastLen(){
		return legs.get(legs.size() - 1);
	}
	
	public int getNumberOfLegs(){
		return legs.size();
	}
	
	public long getTotalTravelTime(){
		Date start = this.getFirstLen().getDepartOn();
		Date end = this.getLastLen().getArriveOn();
		Assert.isTrue(end.compareTo(start) > 0, "end time should bigger than start time.");
		return end.getTime() - start.getTime();
	}
}

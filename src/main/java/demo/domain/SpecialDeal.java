package demo.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SpecialDeal {
	private Airport departFrom;
	private Airport arriveAt;
	
	private BigDecimal totalCost;
	private Date beginOn;
	private Date endOn;
	
	public SpecialDeal(Airport departFrom, Airport arriveAt, BigDecimal totalCost, Date beginOn, Date endOn){
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.totalCost = totalCost;
		this.beginOn = beginOn;
		this.endOn = endOn;
	}
	public Airport getDepartFrom() {
		return departFrom;
	}
	public Airport getArriveAt() {
		return arriveAt;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public Date getBeginOn() {
		return beginOn;
	}
	public Date getEndOn() {
		return endOn;
	}
	
	public boolean isValidNow(){
		return isValidOn(new Date());
	}
	public boolean isValidOn(Date date) {
		Date dateCopy = new Date(date.getTime());
		return ((dateCopy.equals(beginOn) || dateCopy.after(beginOn))
					&& (dateCopy.equals(endOn) || dateCopy.before(endOn)));
	}
	}

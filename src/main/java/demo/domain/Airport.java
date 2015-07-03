package demo.domain;

public class Airport {
	private String name;
	private String airportCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	@Override 
	public String toString(){
		return name+" ("+airportCode+")";
	}
}

package demo.service;

import java.util.List;

import demo.domain.Flight;
import demo.domain.FlightSearchCriteria;
import demo.domain.SpecialDeal;

public interface FlightService {
	List<SpecialDeal> getSpecialDeals();
	List<Flight> findFlights(FlightSearchCriteria search);
}

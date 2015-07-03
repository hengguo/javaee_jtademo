package demo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import demo.domain.Airport;
import demo.domain.Flight;
import demo.domain.FlightLen;
import demo.domain.FlightSearchCriteria;
import demo.domain.SpecialDeal;
import demo.service.FlightService;

@Service("flightService")
public class FlightServiceImpl implements FlightService{

	public List<SpecialDeal> getSpecialDeals() {
		Airport a1 = new Airport();
		a1.setName("a1");
		Airport a2 = new Airport();
		a2.setName("a2");
		SpecialDeal deal1 = new SpecialDeal(a1, a2, new BigDecimal(123), new Date(), new Date());
		SpecialDeal deal2 = new SpecialDeal(a2, a1, new BigDecimal(123.123), new Date(), new Date());
		List l = new ArrayList();
		l.add(deal2);
		l.add(deal1);
		
		return l;
		
	}

	public List<Flight> findFlights(FlightSearchCriteria search) {
		Airport from = new Airport();
		from.setName(search.getDepartFrom());
		Airport at = new Airport();
		at.setName(search.getArriveAt());
		FlightLen len1 = new FlightLen(from, at, search.getDepartOn(), search.getArriveOn());
		Flight flight1 = new Flight(Arrays.asList(len1), new BigDecimal(123.43));
		return Arrays.asList(flight1);
	}

}

package demo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import demo.domain.Flight;
import demo.domain.FlightSearchCriteria;
import demo.service.FlightService;

@Controller
@RequestMapping("/flights")
public class FlightsController {
	
	@Resource
	private FlightService flightService;

	@RequestMapping("/searchView")
	public String searchView(){
		return "menu01/page1";
	}
	
	@RequestMapping("/specialsView")
	public String specialsView(){
		return "menu02/userList";
	}
	
	@RequestMapping("/specials")
	public ModelAndView getSpecials(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView("home");
//		mav.addObject("specials", flightService.getSpecialDeals());
//		return mav;
		return new ModelAndView("menu02/userList", "specials", flightService.getSpecialDeals());
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String departFrom,
			@RequestParam String arriveAt,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")Date departOn,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")Date arriveOn){
		
		FlightSearchCriteria search = new FlightSearchCriteria();
		search.setDepartFrom(departFrom);
		search.setArriveAt(arriveAt);
		search.setDepartOn(departOn);
		search.setArriveOn(arriveOn);
		List<Flight> flights = flightService.findFlights(search);
		
		return new ModelAndView("menu01/flights", "flights", flights);
	}

}

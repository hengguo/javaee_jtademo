package demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value="/main")
	public ModelAndView goMain(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("main", "", null);
	}

}

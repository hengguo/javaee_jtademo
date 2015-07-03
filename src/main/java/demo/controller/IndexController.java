package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/index")
public class IndexController {
	
	@RequestMapping(value="/getNorth")
	public String getNorth(){
		return "layout/north";
	}
	
	@RequestMapping(value="/getWest")
	public String getWest(){
		return "layout/west";
	}
	
	@RequestMapping(value="/getMain")
	public String getMain(){
		return "main";
	}
	@RequestMapping(value="/main")
	public String goMain(){
		return "index";
	}
	
}

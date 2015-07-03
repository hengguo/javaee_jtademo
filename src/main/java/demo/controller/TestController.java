package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

import demo.service.TestService;

@Controller
@RequestMapping(value="/test")
public class TestController {
	private static int ctn = 0;
	@Resource
	private TestService testService;
	public TestController(){
		System.out.println("TestController initialized count = " + ctn);
		TestController.ctn ++;
	}

}

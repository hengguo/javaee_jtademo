package demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import demo.domain.User;

@Controller
@RequestMapping(value="/menu01")
public class Menu01Controller {
	
	
	@RequestMapping(value = "/page1", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView page1(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("menu01/page1");
	}
	
	@RequestMapping(value = "/page2", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView page2(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		u1.setCreateTime(new Date());u1.setName("A1");
		User u2 = new User();
		u2.setCreateTime(new Date());u2.setName("A2");
		users.add(u1);users.add(u2);
		request.setAttribute("test", "ttt");
		return new ModelAndView("userList", "users", users);
	}
	
	@RequestMapping(value="/autocompleteView", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView autocompleteView(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("menu01/autocomplete");
	}
}

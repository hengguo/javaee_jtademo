package demo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.service.MenuService;
import demo.util.JsonUtil;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	private static String MENU_ALL = "all";
	
	
	@RequestMapping(value = "/list", produces = "text/plain;charset=utf-8")
	public @ResponseBody
	String list(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		try {
			Map<String, Object> map = null;
			List list = this.menuService.list(map);
			result = JsonUtil.javaObjectToJsonString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

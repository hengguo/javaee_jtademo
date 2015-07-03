package demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import demo.common.page.Page;
import demo.domain.User;
import demo.service.UserService;
import demo.util.JsonHelper;
import demo.util.JsonUtil;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private Logger LOG = Logger.getLogger(UserController.class);
	private static int ctn = 0;

	public UserController() {
		System.out.println("UserController initialized count = " + ctn);
		UserController.ctn++;
	}
	@Resource
	private UserService userService;
	
	@RequestMapping("dynamicUserView")
	public String dynamicUserView(){
		return "dynamicDataGridColumn";
	}
	
	@RequestMapping("editableDatagridView")
	public String editableDatagridView(){
		return "editableDataGridColumn";
	}
	@RequestMapping("editableDatagridView2")
	public String editableDatagridView2(){
		return "editableDataGridColumn2";
	}
	@RequestMapping("footerDataGridView")
	public String footerDataGridView(){
		return "footerDataGrid";
	}
	@RequestMapping(value="/getUsers")
	public ModelAndView getUsers(HttpServletRequest request, HttpServletResponse response){
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		u1.setCreateTime(new Date());u1.setName("A1");
		User u2 = new User();
		u2.setCreateTime(new Date());u2.setName("A2");
		users.add(u1);users.add(u2);
		request.setAttribute("test", "ttt");
		return new ModelAndView("userList", "users", users);
		
	}
	
	
	@RequestMapping(value="/getDynamicUsers", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getDynamicUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
		LOG.info("getDynamicUsers 进来了");
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("t1", "aaaa");
		List<String> list = new ArrayList<String>();
		list.add("a");list.add("b");
		list.add("c");list.add("dddddd");
		map.put("l", list);
		map.put("uid", "100");
		Page page = new Page();
		page.setParam(map);
		try{
		    userService.rollbackOper();
		    return "";
//			List<User> users =userService.selectUsersPageList(page);
//			return JsonUtil.writeListOBJToDataGrid(page.getTotalResult(), users);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping(value="/rollbackOper", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String rollbackOper(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            userService.rollbackOper();
            return "";
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }
	
	@RequestMapping(value="/getFooter", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getFooter(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Page page = new Page();
		List<User> users =userService.selectUsersPageList(page);
		JSONObject object = new JSONObject();
		object.put("total", users.size());
    	object.put("rows", JsonHelper.getJsonString4Object(JsonUtil.toGJson(users), "yyyy-MM-dd HH:mm:ss"));
    	float totalPrice = 0;
    	for(User user : users){
    		totalPrice += user.getId();
    	}
		object.put("footer", "[{\"name\":\"总计（元）：\",\"password\":" + totalPrice + "}]");
		return object.toString();
	}
	
	@RequestMapping(value="/nullReturn", produces = "text/plain;charset=UTF-8")
	public void nullReturn(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = (Map<String, Object>) request.getAttribute("SPRING");
		Page page = new Page();
        System.out.println("recive message:\t"+map);
		List<User> users =userService.selectUsersPageList(page);
	}
	
	@RequestMapping("deleteAndInsert")
	public void deleteAndInsert(HttpServletRequest request){
		User user = new User();
		user.setId(101);
		user.setName("D");
		user.setPassword("D");
		this.userService.deleteAndInsert(user);
	}
}

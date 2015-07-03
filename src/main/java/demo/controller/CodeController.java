package demo.controller;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import demo.common.Constrants;
import demo.common.page.Page;
import demo.domain.Code;
import demo.service.CodeService;
import demo.util.JsonUtil;


@Controller
@RequestMapping(value="/code")
public class CodeController {
	@Resource
	private CodeService hxCodeService;
	
	/*
	 * 获取码表下拉框内容{codeKey}
	 */
	@RequestMapping(value="/getCombobox/{codeKey}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getCombobox(@PathVariable String codeKey, String value, HttpServletRequest request){
		if("true".equalsIgnoreCase(value)) value = "1";
		if("false".equalsIgnoreCase(value)) value = "0";
		Map<String, String> map = Constrants.CODEMAP.get(codeKey);
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iter = set.iterator();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		
		while(iter.hasNext()){
			Entry<String, String> entry = iter.next();
			JSONObject object = new JSONObject();
			object.put("value", entry.getKey());
			object.put("text", entry.getValue());
			if(entry.getKey().equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取所有组织机构
	 */
	@RequestMapping(value="/getList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getOrgCombobox(HttpServletRequest request, String q){
		List<Map<String, String>> list = hxCodeService.getList(q);
		JSONArray array = new JSONArray();
		
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code_key"));
			object.put("text", map.get("code_value"));
			if(map.get("code_key").equals(q)){
				object.put("selected", true);
			}
			array.add(object);
			if(array.size()==10){
				break;
			}
		}
		return array.toString();
	}
	
	@RequestMapping(value="/hxCodeView")
	public String hxCodeView(){
		return "basicData/hxCode/hxCodeList";
	}
	
	@RequestMapping(value="/getHxCodePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxCodePageList(HttpServletResponse response, Page page) throws Exception{
		List<Map<String, Object>> list = hxCodeService.getHxCodePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/updateView")
	public ModelAndView updateView(Code hxCode) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxCode/hxCodeUpdate");
		hxCode.setCodeName(URLDecoder.decode(hxCode.getCodeName(), "UTF-8"));
		mav.addObject(hxCode);
		return mav;
	}
	
	@RequestMapping(value="/getHxCodeSettingByCodeId/{codeId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxCodeSettingByCodeId(@PathVariable String codeId) throws Exception{
		List<Map<String, Object>> list = hxCodeService.getHxCodeSettingByCodeId(codeId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	}

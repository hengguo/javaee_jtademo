package demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.service.ConcurrencyService;
/**
 * 并发测试
 */
@Controller
@RequestMapping("/async")
public class ConcurrencyController {
	@Resource
	private ConcurrencyService concurrencyService;
	
    @RequestMapping(value = "/getBatch1")
    @ResponseBody
    public String getBatch1(HttpServletRequest request, HttpServletResponse response)
               throws Exception {
      return this.concurrencyService.updateBatchThenReturn().toString();
    }  
    
    @RequestMapping(

    		)
    @ResponseBody
    public String getBatch2(HttpServletRequest request, HttpServletResponse response)
               throws Exception {
      return this.concurrencyService.updateBatchThenReturn().toString();
    }  

}

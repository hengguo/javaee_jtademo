package demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.service.SyncService;
/**
 * 不同的Controller对同一个service进入引用，这样service同步方法是不是同步的呢？？？
 * 
 * 事实证明只有service方法是同步的话  任务注入SyncService接口的Controller调用其同步方法的都要阻塞并等待其完成
 */
@Controller
@RequestMapping("/syncopy")
public class SyncControllerCopy {
	@Resource
	private SyncService syncService;
	
	private int singletonInt=1;
	
	/**
	 * 证明controller默认是单例的！！
	 */
    @RequestMapping(value = "/test")
    @ResponseBody
    public String singleton(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data=request.getParameter("data");
        if(data!=null&&data.length()>0){
            try{
             int paramInt= Integer.parseInt(data);
            singletonInt = singletonInt + paramInt;
            }
            catch(Exception ex){
                singletonInt+=10;
            }
        }else{
            singletonInt+=1000;
        } 
        return String.valueOf(singletonInt);
   }
    
    @RequestMapping(value = "/syncOnController")
    @ResponseBody
    public synchronized String syncOnController(HttpServletRequest request, HttpServletResponse response)
               throws Exception {
      String sleep = request.getParameter("sleep");
      if (sleep.equals("on")) {
          Thread.currentThread().sleep(20000);
           return "sleep on";
       } else {
           return sleep;
      }
    }  
    
    @RequestMapping(value = "/syncOnService")
    @ResponseBody
    public String syncOnService(HttpServletRequest request, HttpServletResponse response)
               throws Exception {
      return this.syncService.syncOnService(request);
    }  

}

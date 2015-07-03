package demo.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import demo.service.SyncService;

@Service("syncService")
public class SyncServiceImpl implements SyncService {

	@Override
	public synchronized String syncOnService(HttpServletRequest request) throws InterruptedException {
		String sleep = request.getParameter("sleep");
	      if (sleep.equals("on")) {
	          Thread.currentThread().sleep(20000);
	           return "sleep on";
	       } else {
	           return sleep;
	      }
	}

}

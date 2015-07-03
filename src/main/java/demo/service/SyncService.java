package demo.service;

import javax.servlet.http.HttpServletRequest;

public interface SyncService {

	String syncOnService(HttpServletRequest request) throws InterruptedException;
	
}

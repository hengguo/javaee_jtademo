package demo.util;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import demo.common.DataSourceContextHolder;

public class SysUserFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			DataSourceContextHolder.setDataSourceType("dataSource2");
			chain.doFilter(request, response);
		}finally{
			
		}
	}

	@Override
	public void destroy() {
		
	}

}

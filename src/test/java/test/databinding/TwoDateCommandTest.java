package test.databinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.ServletRequestDataBinder;

public class TwoDateCommandTest extends TestCase {
	
	private TwoDateCommand bean;
	private MockHttpServletRequest request;
	private ServletRequestDataBinder binder;
	public void setUp() throws Exception {
		bean  = new TwoDateCommand();
		request = new MockHttpServletRequest();
		binder = new ServletRequestDataBinder(bean, "bean");
	}
	
	public void test() throws ParseException{
		SimpleDateFormat firstDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat secondDF = new SimpleDateFormat("yyyy-dd-MM");

		Date firstExcepted = firstDF.parse("2001-01-22");
		CustomDateEditor firstDateEditor = new CustomDateEditor(secondDF, true);
		
		binder.registerCustomEditor(Date.class, "firstDate", firstDateEditor);
		request.addParameter("firstDate", "2001-01-22");
		
		binder.bind(request);
		
		System.out.println(bean.getFirstDate());
		assertEquals(firstExcepted, bean.getFirstDate());
	}

}

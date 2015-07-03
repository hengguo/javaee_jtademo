/**   
* @Title: XClass.java 
* @Package test.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Wang.Hengguo  
* @date 2015年2月6日
* @version V1.0   
*/
package test.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Package test.service 
 * @author Wang.Hengguo
 * @date 2015年2月6日下午5:20:37
 */
public class XClass {
	public static void main(String[] args) throws ParseException {
//	    BigDecimal a = BigDecimal.valueOf(0).add(BigDecimal.valueOf(168.01).multiply(BigDecimal.valueOf(1)));
//	    System.err.println(a);
//        BigDecimal b2 = new BigDecimal(String.valueOf(168.01));
//        System.out.println(b2);
//        BigDecimal bd1 = new BigDecimal("10.123");  
//        System.out.println( 1.22+0.89);  
//	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//	    Date st = sf.parse("20150406");
//	    System.out.println(st.toLocaleString());
//	    
	   //Date s2 = sf.format("20150406")
	    
	    System.out.println(Calendar.getInstance().getTimeInMillis());
	    System.out.println(new Date().getTime());
	}

}

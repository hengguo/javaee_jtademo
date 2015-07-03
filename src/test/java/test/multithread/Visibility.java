/**   
* @Title: NoVisibility.java 
* @Package test.multithread 
* @author Wang.Hengguo  
* @date 2015年7月1日
* @version V1.0   
*/
package test.multithread;

import java.util.Calendar;
import java.util.Date;


/** 
 * @PackageName : test.multithread
 * @ClassName: NoVisibility 
 * @author Wang.Hengguo
 * @date 2015年7月1日
 *  
 */
public class Visibility {
    public static void main(String[] args) {
//        Date s = Calendar.getInstance().getTime();
//        Long sum = 0L;
//        for(long i = 0; i<Integer.MAX_VALUE; i ++) {
//            sum += i;
//        }
//        Date e = Calendar.getInstance().getTime();
//        System.out.println(e.getTime()  - s.getTime());
        
        Date s1 = Calendar.getInstance().getTime();
        long sum1 = 0l;
        for(long i = 0; i<Integer.MAX_VALUE; i ++) {
            sum1 += i;
        }
        Date e1 = Calendar.getInstance().getTime();
        System.out.println(e1.getTime()  - s1.getTime());
        
    }

}

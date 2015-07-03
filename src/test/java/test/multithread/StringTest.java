/**   
* @Title: NoVisibility.java 
* @Package test.multithread 
* @author Wang.Hengguo  
* @date 2015年7月1日
* @version V1.0   
*/
package test.multithread;



/** 
 * @PackageName : test.multithread
 * @ClassName: NoVisibility 
 * @author Wang.Hengguo
 * @date 2015年7月1日
 *  
 */
public class StringTest {
    public static void main(String[] args) {
        // String s1 = "1";
        // String s2 = "1";
        String s1 = new String("1");
        String s2 = "1";

        System.out.println(s1.equals(s2));
    }

}

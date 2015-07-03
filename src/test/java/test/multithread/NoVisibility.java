/**   
* @Title: NoVisibility.java 
* @Package test.multithread 
* @author Wang.Hengguo  
* @date 2015年7月1日
* @version V1.0   
*/
package test.multithread;

import java.util.Iterator;

/** 
 * @PackageName : test.multithread
 * @ClassName: NoVisibility 
 * @author Wang.Hengguo
 * @date 2015年7月1日
 *  
 */
public class NoVisibility {
    private static volatile boolean ready;
    private static int number;
    
    public static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready){
               Thread.yield(); 
            }
            System.out.println(number);
        }
    }
    
    public static void main(String []args) {
        for (int i=0; i<100;  i++) {
            
            new ReaderThread().start();
            
            number = 42;
            ready = true;
        }
    }

}

/**   
* @Title: PrimeGenerater.java 
* @Package test.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Wang.Hengguo  
* @date 2015年2月3日
* @version V1.0   
*/
package test.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一个仅运行2秒的素数生成器<p>
 * <b>！！如何这个方法是调用的是一个阻塞方法，然后任务将永远不会检查取消标志，因此永远不会结束。</b>
 * @Package test.service 
 * @author Wang.Hengguo
 * @date 2015年2月3日上午11:02:07
 */
public class PrimeGenerater implements Runnable {

	/**
	 * @author wanghengguo
	 */
	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (primes) {
				primes.add(p);
			}
		}
	}
	
	public void cancel () { cancelled = true;}
	
	public synchronized List<BigInteger> get () {
		return new ArrayList<BigInteger>(primes);
	}
	private List<BigInteger> primes = new ArrayList<BigInteger>();
	private volatile boolean cancelled;
	
	public static void main (String args[]){
		PrimeGenerater generater = new PrimeGenerater();
		new Thread(generater).start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			generater.cancel();
		}
		System.out.println(generater.get());
	}
}

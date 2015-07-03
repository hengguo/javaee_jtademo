/**   
* @Title: Counter.java 
* @Package test.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Wang.Hengguo  
* @date 2015年2月2日
* @version V1.0   
*/
package test.service;

/**
 * @Package test.service 
 * @author Wang.Hengguo
 * @date 2015年2月2日下午1:54:39
 */
public class Counter {
	private long value = 0;
	public long getValue(){
		return value;
	}
	public synchronized long increment() throws IllegalStateException{
		
		if(value == Long.MAX_VALUE){
			throw new IllegalStateException("sxxx");
		}
		return ++value;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Counter c = new Counter();
		for (int i = 0; i < 5000; i++) {
			Runnable r = new Runnable(){/**
			 * {@inheritDoc}
			 */
				@Override
				public void run() {
					try {
						System.out.print(c.increment()+"\n");
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
				}
			};
			new Thread(r).start();
		}
	}

}

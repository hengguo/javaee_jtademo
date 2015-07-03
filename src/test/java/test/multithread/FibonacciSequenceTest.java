package test.multithread;

/**
 * 斐波那契数列
 *
 */
public class FibonacciSequenceTest {
	private static final int a1 = 1;//第一项默认值
	private static final int a2 = 2;//第二项默认值
	
	public static void main(String[] args) {
		//递归
		System.out.println("递归求和：" + FibonacciSequenceTest.getSumByFibonacciSequence(45));
		//迭代
		System.out.println("迭代求和：" + FibonacciSequenceTest.getSumByNonRecursive(45));
	}
	/**
	 * 斐波那契数列求和：迭代
	 * @return
	 */
	public static int getSumByNonRecursive(int n) {
		long startTime = System.currentTimeMillis();
		if(n == 1) {
			return a1;
		}
		int sum = a1 + a2;
		if(n == 2) {
			return sum;
		}
		int firstNum = a1;
		int secondNum = a2;
		int lastNum = 0;
		for(int i = 3; i <= n ;i++) {
			//迭代关系(an = an-2 + an-1; an-2=an-1; an-1=an;)
			lastNum = firstNum + secondNum;
			firstNum = secondNum;
			secondNum = lastNum;
			sum += lastNum;
		}
		System.out.println("迭代求和耗时：" + (System.currentTimeMillis() - startTime) + "ms");//0ms
		return sum;
	}
	
	/**
	 * 斐波那契数列前n项和（sn = a1 + a2+...+ an) 递归
	 * @return
	 */
	public static int getSumByFibonacciSequence(int n) {
		long startTime = System.currentTimeMillis();
		if(n == 1) {
			return a1;
		}
		int sum = 1;
		int lastNum = 0;
		for(int i = 2; i <= n; i++) {
			lastNum = FibonacciSequenceTest.getLastNum(i);
			sum += lastNum;
		}
		System.out.println("递归求和耗时：" + (System.currentTimeMillis() - startTime) + "ms");//14839ms
		return sum;
	}
	
	/**
	 * 递归：第n项的值（递推公式：an = a(n - 1) + a(n - 2)）
	 * 1 2 3 5 8 13 21
	 * @param n
	 * @return
	 */
	public static int getLastNum(int n) {
		if(n == 1) {
			return a1;
		}
		if(n == 2) {
			return a2;
		}
		int lastListNum = getLastNum(n - 1) + getLastNum(n - 2);
		return lastListNum;
	}
}

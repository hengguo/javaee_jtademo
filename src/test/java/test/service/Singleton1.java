package test.service;

public class Singleton1 {
	private static final Singleton1 instance = new Singleton1();

	// 私有的默认构造函数
	private Singleton1() {
	}
	
	public Singleton1(String a){
		
	}

	// 静态工厂方法
	public static Singleton1 getInstance() {

		return instance;
	}
}

class A extends Singleton1{

	/**
	 * @param a
	 */
	public A(String a) {
		super(a);
	}
	
}
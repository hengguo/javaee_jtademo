package test.map;

import demo.map.MyMap;

public class Test {

	public static void main(String[] args) {
		MyMap<String, String> mm = new MyMap<String, String>();
		Long aBeginTime = System.currentTimeMillis();// 记录BeginTime
		for (int i = 0; i < 100; i++) {
//			mm.put("" + i, "" + i * 100);
			mm.put("" + i, "1");
		}
		Long aEndTime = System.currentTimeMillis();// 记录EndTime
		System.out.println("insert time-->" + (aEndTime - aBeginTime));

		Long lBeginTime = System.currentTimeMillis();// 记录BeginTime
		System.out.println(mm.get("" + 10));
		Long lEndTime = System.currentTimeMillis();// 记录EndTime
		System.out.println("seach time--->" + (lEndTime - lBeginTime));
	}
}

package test.databinding;

import net.sf.json.JSONObject;

public class JSONObjectTest {
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "huangwuyi");
		jsonObject.put("sex", "男");
		jsonObject.put("QQ", "413425430");
		jsonObject.put("Min.score", new Integer(99));
		jsonObject.put("nickname", "梦中心境");
		System.out.println(jsonObject);
//		return jsonObject;
	}

}

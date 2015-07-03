package test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.mysql.jdbc.CallableStatement;

import demo.util.SpringUtil;

public class ServiceTest {
	 @Test
	 public void appSync(){
		 String pre = "user/getDynamicUsers";
	        String url = "http://localhost:8090/springmvc_demo/" + pre;
	        JSONObject jo = new JSONObject();
	        jo.put("A", "aaa");
	        try {
	            HttpPost httppost = new HttpPost(url);
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	            HttpResponse httpResponse = new DefaultHttpClient().execute(httppost);
	            HttpEntity entity2 = httpResponse.getEntity();
	            System.out.println(EntityUtils.toString(entity2));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public static void main(String []args) throws SQLException, Exception{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		 String sql = "insert into code_setting values (?,?,?)";
		 PreparedStatement stmt = conn.prepareStatement(sql);
		 for (int i = 40000; i < 80000; i++) {
			stmt.setString(1, "dg");
			stmt.setString(2, i+"");
			stmt.setString(3, i+"");
			stmt.executeUpdate();
		}
		 stmt.close();
		 conn.close();
	 }

}

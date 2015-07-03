package test.http;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import demo.util.AESAPPUtils;
import demo.util.GzipAESUtil;


public class AspectDemoTest {
    
    
    @Test
    public void appSync(){
        String pre = "user/nullReturn";
        String url = "http://localhost:8090/springmvc_maven/" + pre;
        JSONObject jo = new JSONObject();
        jo.put("A", "aaa");
        try {
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("SPRING", AESAPPUtils.encryptAES(jo.toString())));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httppost);
            HttpEntity entity2 = httpResponse.getEntity();
            String result = EntityUtils.toString(entity2);
            System.out.println(GzipAESUtil.decryptAESThenUnCompress(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

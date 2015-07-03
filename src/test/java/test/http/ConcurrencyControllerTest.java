package test.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class ConcurrencyControllerTest {
    
    
    public static void main(String []args){
        String pre = "async/getBatch1";
        String url = "http://localhost:8090/springmvc_demo/" + pre;
        Date start = new Date();
        try {
            final HttpPost httppost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            for(int i =0 ;i<2; i++){
            	Thread r = new Thread( new Runnable(){
					@Override
					public void run() {
						try {
							HttpResponse httpResponse = new DefaultHttpClient().execute(httppost);
							HttpEntity entity2 = httpResponse.getEntity();
							String result = EntityUtils.toString(entity2);
							System.out.println("************************************************* "+result);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}});
				r.start();
            }
         System.err.println(new Date().getTime() - start.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

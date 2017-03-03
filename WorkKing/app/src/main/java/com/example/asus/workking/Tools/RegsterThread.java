package com.example.asus.workking.Tools;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by asus on 2017/2/11.
 */

public class RegsterThread extends Thread {
    private final static String URL = "http://115.28.80.81/app/check.php";

    private String mName;
    private String mPass;
    private  boolean mFlag = false;
    private String mSelectPeople;

    @Override
    public void run() {
        try {

            HttpPost request = new HttpPost(URL);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("action", "register"));
            params.add(new BasicNameValuePair("name", mName));
            params.add(new BasicNameValuePair("password", mPass));
            params.add(new BasicNameValuePair("id", "14070642"));
            params.add(new BasicNameValuePair("flag", mSelectPeople));
            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpResponse response = new DefaultHttpClient().execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = EntityUtils.toString(response.getEntity());
                System.out.println("JSON1-------->" + str);
                JSONObject jsonObject = new JSONObject(str);
                System.out.println("STATUS------------------->" + jsonObject.getBoolean("status"));
                mFlag = jsonObject.getBoolean("status");

            }else{

                System.out.println("Server is not response");
            }
        }catch (Exception e){}

    }

    public boolean getFlag(){
        return this.mFlag;
    }

    public RegsterThread(String mName , String mPass , String mSelectPeople){
        this.mName = mName;
        this.mPass = mPass;
        this.mSelectPeople = mSelectPeople;
    }
}

package com.example.demo.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class HttpsClientUtil {
    @SuppressWarnings("resource")
    public static String doPost(String url, List<NameValuePair> pair, String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{

            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(pair, "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("传输对象:"+pair.toString()+"\n"+"返回结果:"+result);
        return result;
    }
}

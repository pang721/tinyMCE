package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class test2Controller {

    @RequestMapping("js")
    public  String max() {
        String url = "http://127.0.0.1:8080/find/1";
        System.out.println("URL："+url);
        StringBuffer json = new StringBuffer();
        try {
            //实例一个url和URLConnection
            URL oracle = new URL(url);
            //打开链接
            URLConnection yc = oracle.openConnection();
            //输入流作参数传进InputStreamReader并用BufferedReader接受
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            //一直读到空，并设置流编码是UTF8
            while ( (inputLine = in.readLine()) != null) {
                json.append(new String(inputLine.getBytes(),"utf-8"));
            }
            //记得关闭连接
            in.close();
        } catch (Exception e)  {
            e.printStackTrace();
        }
        try {
            System.out.println(json);
           /* JSONArray jn =  JSONArray.parseArray(json.toString());
            if(jn.size()>0){
                for (int i = 0; i < jn.size(); i++) {
                    JSONObject jo = (JSONObject) jn.get(i);
                    System.out.println(jo.get("data"));
                    //System.out.println(jo.get("fdName"));
                }
                System.out.println(jn);
            }
            System.out.println("数据大小："+jn.size());*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接超时！");
        }
        return "show";
    }

}

package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.IQueryService;
import com.example.demo.util.HttpsClientUtil;
import com.example.demo.util.QRCodeUtil;
import com.example.demo.util.file.FileUploadUtil;
import com.example.demo.util.file.MD5FileUtil;
import com.example.demo.util.other.DateUtils;
import com.example.demo.util.other.StringUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class testController {
    @Autowired
    private IQueryService iQueryService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) throws Exception {
        String url= "";  // 存放在二维码中的内容,链接或者参数
        String imgPath = null; //嵌入二维码的图片绝对路径也可以不放
        String destPath = "C:\\Users\\pangx\\Desktop\\files\\1.jpg";// 生成的二维码的路径及名称
        String text = "123";
        QRCodeUtil.encode(url, imgPath, destPath, true);
        String str = QRCodeUtil.decode(destPath);
        System.out.println(str);
    }



    @RequestMapping("/tinyMCE")
    public String test2(){
        return "tinyMCE";
    }

    @RequestMapping("/redis")
    public void redis(){
        Object o = redisTemplate.opsForValue().get("1");
        System.out.println(o);
    }



    /**
     * 将对象转换成Map<String, String>格式
     *
     * @param obj
     * @return
     */
    public static HashMap<String, String> getNamValMap(Object obj) {
        HashMap<String, String> map = new HashMap<>();
        Field[] fieldArr = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fieldArr) {
                field.setAccessible(true);
                if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                    map.put(field.getName(), field.get(obj).toString());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取当前时间戳（秒级/10位）
    public static String getTimestamp(Date date) {
        if (null == date) {
            return "0";
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return timestamp;
    }


    @RequestMapping("/upload")
    public void upload(HttpServletResponse response, HttpServletRequest request){
        FileUploadUtil fileUploadUtil=new FileUploadUtil();
        try {
            List<String> uploadImg = fileUploadUtil.uploadImg(request,response);
            Map<String,String> map=new HashMap<>();
            map.put("location",uploadImg.get(0));
            String result = JSON.toJSONString(map);
            System.out.println(result);
            Writer writer=response.getWriter();
            writer.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/uploadFile")
    public void uploadFile(HttpServletResponse response, HttpServletRequest request){
        FileUploadUtil fileUploadUtil=new FileUploadUtil();
        try {
            List<String> uploadFile = fileUploadUtil.uploadFile(request,response);
            Map<String,String> map=new HashMap<>();
            map.put("location",uploadFile.get(0));
            String result = JSON.toJSONString(map);
            System.out.println(result);
            Writer writer=response.getWriter();
            writer.write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "uploadUEditorImage")
    public void uploadUEditorImage(@RequestParam(value = "upfile", required = false) MultipartFile file,
                                   HttpServletResponse response, HttpServletRequest request) throws Exception {
        //request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        JSONObject json = new JSONObject();
        PrintWriter out = response.getWriter();

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
            String date = simpleDateFormat.format(new Date());
            String strPath = "files," + date;
            String filepath = "C:\\Users\\pangx\\Desktop\\" + strPath.replace(',', File.separatorChar);
            String newFileName = MD5FileUtil.getMD5String(file.getBytes());
            String fileType=file.getContentType();
            fileType=fileType.substring(fileType.lastIndexOf('/')+1);
            String newFilePath=filepath + File.separatorChar +newFileName+"."+fileType;
            File dest = new File(filepath);
            if (!dest.exists()) {
                dest.mkdirs();
            }
           File uploadFile = new File(newFilePath);
            if(uploadFile.exists()){
                uploadFile.delete();
            }

            FileCopyUtils.copy(file.getBytes(), uploadFile);

            json.put("state", "SUCCESS");
            json.put("title", newFileName+"."+fileType);
            System.out.println(file.getName());
            //json.put("url",strPath.replace(',', File.separatorChar)+ File.separatorChar +newFileName+"."+fileType);//修改为自己的图片保存路径
            json.put("url",newFilePath);//修改为自己的图片保存路径
            json.put("original",newFileName+"."+fileType);

            out.print(json.toString());
            //logger.info("上传图片结束，位置："+path);
        } catch (Exception e) {
            json.put("state", "上传图片出错");
            out.print(json.toString());
        }
    }



}

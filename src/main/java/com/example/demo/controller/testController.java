package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.*;
import com.example.demo.service.IQueryService;
import com.example.demo.util.HttpsClientUtil;
import com.example.demo.util.QRCodeUtil;
import com.example.demo.util.encrypt.SignUtil;
import com.example.demo.util.file.FileUploadUtil;
import com.example.demo.util.file.MD5FileUtil;
import com.example.demo.util.other.DateUtils;
import com.example.demo.util.other.StringUtil;
import com.example.demo.util.page.Page;
import com.example.demo.util.page.PagingUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static void main(String[] args) throws Exception {
        String url= "https://openapi.linkingcloud.cn/oauth2/authorize?app_id=LCOP20200402181710759&redirect_uri=http%3a%2f%2fcloud07.linkingcloud.cn%3a9027%2ffkpt.ashx&response_type=code&scope=auth_base,auth_userinfo&state=lc";  // 存放在二维码中的内容,链接或者参数
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














        @RequestMapping(value = "/establish", method = RequestMethod.GET)
    public void establish(HttpServletResponse response, HttpServletRequest request){
       String content=request.getParameter("content");
       iQueryService.insertNotice(content);
    }
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Map<String, Object>  find(Model model, @PathVariable(value = "id") int id){
        Notice records= iQueryService.findNotice(id);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code","0");
        resultMap.put("data",records);
        //model.addAttribute("resultMap",resultMap);
        return resultMap;
    }


    @RequestMapping(value = "/finds", method = RequestMethod.GET)
    public Map<String, Object>  find(HttpServletRequest request){
        String zpmc=request.getParameter("zpmc");
        Map<String, Object> ppMap = new HashMap<String, Object>();
        ppMap.put("zpmc",zpmc);
        List<Vaccin> v= iQueryService.finds(ppMap);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code","0");
        // resultMap.put("data",records);
        //model.addAttribute("resultMap",resultMap);
        return resultMap;
    }

    /**
     * 添加疫苗说明
     * @param v
     */
    @RequestMapping(value = "/addVaccine", method = RequestMethod.GET)
    public void  addVaccine(Vaccin v){
        Vaccin newVaccin=generate(v);
        try {
            iQueryService.addVaccine(newVaccin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除疫苗说明
     * @param id
     */
    @RequestMapping(value = "/deleteVaccine/{id}", method = RequestMethod.GET)
    public void deleteVaccine(@PathVariable(value = "id") int id){
        try {
            iQueryService.deleteVaccine(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改疫苗说明
     * @param v
     */
    @RequestMapping(value = "/updateVaccine", method = RequestMethod.GET)
    public void  updateVaccine(Vaccin v){
        Vaccin newVaccin=generate(v);
        try {
            iQueryService.updateVaccine(newVaccin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查看疫苗说明的信息
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/findVaccine/{id}", method = RequestMethod.GET)
    public String findVaccineById(HttpServletRequest request,@PathVariable("id") int id){
        List<Vaccin> vaccins= iQueryService.findVaccine(null,id);
        request.setAttribute("v",vaccins.get(0));
        return "updateVaccine";
    }

    /**
     * 查看所有疫苗说明
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/findVaccine", method = RequestMethod.GET)
    public String findVaccine(HttpServletRequest request, @RequestParam(value="page",required=false,defaultValue="1") int page){
        Page<Vaccin> pageModel = new Page<Vaccin>();
        pageModel.setPageNo(page);
        pageModel.setPageSize(8);
        List<Vaccin> vaccins= iQueryService.findVaccine(pageModel,0);
        String pageStr = PagingUtil.getPagelink(page, pageModel.getTotalPage(), "", "findVaccine");
        request.setAttribute("v",vaccins);
        request.setAttribute("pageStr",pageStr);

        return "showVaccine";
    }

    /**
     * 根据规则转换疫苗说明
     * @param v
     * @return
     */
    public Vaccin generate(Vaccin v){
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"一");
        map.put(2,"二");
        map.put(3,"三");
        map.put(4,"四");
        map.put(5,"五");
        map.put(6,"六");
        map.put(7,"七");
        map.put(8,"八");
        map.put(9,"九");
        map.put(10,"十");
        String zcms="";
        if(v.getZc()<=10){
            zcms = "第"+map.get(v.getZc())+"剂";
        }
        v.setZcms(zcms);
        String jzyl=v.getJzyl().replace(" ","");
        //儿童疫苗 需yl排序
        if(v.getYmlb()==0){
            Double yl=0.00;
            if(!jzyl.trim().equals("出生")){
                if(jzyl.indexOf("周岁")!= -1){
                    jzyl = jzyl.replace("周岁","");
                    yl = Double.parseDouble(jzyl)*12;
                }else if(jzyl.indexOf("周龄")!= -1){
                    jzyl = jzyl.replace("周龄","");
                    yl = Double.parseDouble(jzyl)*7/30;
                }else{
                    jzyl = jzyl.replace("月龄","").replace("月",".").replace("天","");
                    yl=Double.parseDouble(jzyl);
                }
            }

            v.setYl(yl);
        }

        return v;
    }


}

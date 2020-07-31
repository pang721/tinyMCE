package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Notice;
import com.example.demo.service.IQueryService;
import com.example.demo.util.file.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class testController {
    @Autowired
    private IQueryService iQueryService;
    @RequestMapping("/fw")
    public String test(){
        return "index";
    }
    @RequestMapping("/fw2")
    public String test2(){
        return "tinyMCE";
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
}

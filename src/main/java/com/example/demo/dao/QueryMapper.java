package com.example.demo.dao;

import com.example.demo.model.Notice;
import com.example.demo.model.Vaccin;
import com.example.demo.util.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


public interface QueryMapper {
    void insertNotice(@Param("content") String content);
    Notice findNotice(@Param("id") int id);
    void addVaccine(Vaccin v);
    void deleteVaccine(@Param("id")int id);
    void updateVaccine(Vaccin v);
    List<Vaccin> findVaccine(@Param("id")int id, @Param("page") Page<Vaccin> page);
    List<Vaccin> finds(Map<String,Object> ppMap);
}

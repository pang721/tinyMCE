package com.example.demo.service;


import com.example.demo.model.Notice;
import com.example.demo.model.Vaccin;
import com.example.demo.util.page.Page;

import java.util.List;
import java.util.Map;

public interface IQueryService {
    void insertNotice(String content);
    Notice findNotice(int id);
    void addVaccine(Vaccin v);
   void deleteVaccine(int id);
   void updateVaccine(Vaccin v);
   List<Vaccin> findVaccine(Page<Vaccin> page,int id);
   List<Vaccin> finds(Map<String,Object> ppMap);
}

package com.example.demo.dao;

import com.example.demo.model.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface QueryMapper {
    void insertNotice(@Param("content") String content);
    Notice findNotice(@Param("id") int id);
}

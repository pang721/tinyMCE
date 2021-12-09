package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface QueryMapper {
    void insertNotice(@Param("content") String content);
}

package com.example.demo.service;


import com.example.demo.model.Notice;

public interface IQueryService {
    public void insertNotice(String content);
    public Notice findNotice(int id);
}

package com.example.demo.service.serviceImpl;

import com.example.demo.dao.QueryMapper;
import com.example.demo.model.Notice;
import com.example.demo.service.IQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuerySerivceImpl implements IQueryService {
    @Resource
    private QueryMapper queryMapper;
    @Override
    public void insertNotice(String content) {
        queryMapper.insertNotice(content);
    }

    @Override
    public Notice findNotice(int id) {
        return queryMapper.findNotice(id);
    }
}

package com.example.demo.service.serviceImpl;

import com.example.demo.dao.QueryMapper;
import com.example.demo.service.IQueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class QuerySerivceImpl implements IQueryService {
    @Resource
    private QueryMapper queryMapper;
}

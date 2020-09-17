package com.example.demo.service.serviceImpl;

import com.example.demo.dao.QueryMapper;
import com.example.demo.model.Notice;
import com.example.demo.model.Vaccin;
import com.example.demo.service.IQueryService;
import com.example.demo.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public void addVaccine(Vaccin v) {
        queryMapper.addVaccine(v);
    }

    @Override
    public void deleteVaccine(int id) {
        queryMapper.deleteVaccine(id);
    }

    @Override
    public void updateVaccine(Vaccin v) {
        queryMapper.updateVaccine(v);
    }

    @Override
    public List<Vaccin> findVaccine( Page<Vaccin> page,int id) {
        return queryMapper.findVaccine(id,page);
    }

    @Override
    public List<Vaccin> finds(Map<String, Object> ppMap) {
        return queryMapper.finds(ppMap);
    }


}

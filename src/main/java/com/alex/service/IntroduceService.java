package com.alex.service;

import com.alex.bean.Notice;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.alex.bean.Introduce;

import java.util.Date;
import java.util.List;
import com.alex.mapper.IntroduceMapper;
@Service
public class IntroduceService{

    @Resource
    private IntroduceMapper introduceMapper;

    
    public void deleteByPrimaryKey(Integer id) {
        Introduce introduce = introduceMapper.selectByPrimaryKey(id);
        introduce.setDeleted(1);
        introduce.setDelete_time(new Date());
        introduceMapper.updateByPrimaryKey(introduce);
    }

    
    public int insert(Introduce record) {
        return introduceMapper.insert(record);
    }

    
    public int insertSelective(Introduce record) {
        return introduceMapper.insertSelective(record);
    }

    
    public Introduce selectByPrimaryKey(Integer id) {
        return introduceMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Introduce record) {
        return introduceMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Introduce record) {
        return introduceMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatchSelective(List<Introduce> list) {
        return introduceMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Introduce> list) {
        return introduceMapper.batchInsert(list);
    }

    public List<Introduce> list() {
        return introduceMapper.list();
    }
}

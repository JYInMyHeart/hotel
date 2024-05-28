package com.alex.service;

import com.alex.bean.PageBean;
import com.alex.util.PageUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.alex.mapper.DeclareMapper;
import com.alex.bean.Declare;
@Service
public class DeclareService {

    @Resource
    private DeclareMapper declareMapper;

    
    public void deleteByPrimaryKey(Integer id) {
        Declare declare = declareMapper.selectByPrimaryKey(id);
        declare.setDeleted(1);
        declare.setDelete_time(new Date());
        declareMapper.updateByPrimaryKey(declare);
    }

    
    public int insert(Declare record) {
        return declareMapper.insert(record);
    }

    
    public int insertSelective(Declare record) {
        return declareMapper.insertSelective(record);
    }

    
    public Declare selectByPrimaryKey(Integer id) {
        return declareMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Declare record) {
        return declareMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Declare record) {
        return declareMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatchSelective(List<Declare> list) {
        return declareMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Declare> list) {
        return declareMapper.batchInsert(list);
    }

    public List<Declare> list() {
        return declareMapper.list();
    }



    public PageBean<Declare> listByKeyword(int page, int limit, String sort, String asc, String keyword) {
        PageUtil<Declare, DeclareMapper> pageUtil = new PageUtil<>(declareMapper);
        PageBean<Declare> bookPageBean = pageUtil.helper(page, limit, sort, asc, keyword);
        return bookPageBean;
    }

}

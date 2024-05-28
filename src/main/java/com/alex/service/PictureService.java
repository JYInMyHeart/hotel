package com.alex.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List; 
import com.alex.bean.Picture;
import com.alex.mapper.PictureMapper;
@Service
public class PictureService{ 

    @Resource
    private PictureMapper pictureMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return pictureMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Picture record) {
        return pictureMapper.insert(record);
    }

    
    public int insertSelective(Picture record) {
        return pictureMapper.insertSelective(record);
    }

    
    public Picture selectByPrimaryKey(Integer id) {
        return pictureMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Picture record) {
        return pictureMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Picture record) {
        return pictureMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatchSelective(List<Picture> list) {
        return pictureMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Picture> list) {
        return pictureMapper.batchInsert(list);
    }

    public List<Picture> list() {
        return pictureMapper.list();
    }
}

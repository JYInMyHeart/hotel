package com.alex.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.alex.bean.Floor;
import com.alex.mapper.FloorMapper;
@Service
public class FloorService{

    @Resource
    private FloorMapper floorMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return floorMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Floor record) {
        return floorMapper.insert(record);
    }

    
    public int insertSelective(Floor record) {
        return floorMapper.insertSelective(record);
    }

    
    public Floor selectByPrimaryKey(Integer id) {
        return floorMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Floor record) {
        return floorMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Floor record) {
        return floorMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatchSelective(List<Floor> list) {
        return floorMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Floor> list) {
        return floorMapper.batchInsert(list);
    }

    public List<Floor> list() {
        return floorMapper.list();
    }
}

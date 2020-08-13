package com.alex.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.alex.bean.FloorRoom;
import com.alex.mapper.FloorRoomMapper;
@Service
public class FloorRoomService{

    @Resource
    private FloorRoomMapper floorRoomMapper;

    
    public int insert(FloorRoom record) {
        return floorRoomMapper.insert(record);
    }

    
    public int insertSelective(FloorRoom record) {
        return floorRoomMapper.insertSelective(record);
    }

    
    public int batchInsert(List<FloorRoom> list) {
        return floorRoomMapper.batchInsert(list);
    }

    public void delete(Integer roomId, Integer floorId) {
        floorRoomMapper.delete(roomId,floorId);
    }
}

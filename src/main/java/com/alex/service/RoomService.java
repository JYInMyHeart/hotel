package com.alex.service;

import com.alex.bean.Floor;
import com.alex.bean.FloorRoom;
import com.alex.bean.Room;
import com.alex.bean.RoomVO;
import com.alex.mapper.FloorMapper;
import com.alex.mapper.FloorRoomMapper;
import com.alex.mapper.RoomMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private FloorMapper floorMapper;

    @Resource
    private FloorRoomMapper floorRoomMapper;

    public int deleteByPrimaryKey(Integer id) {
        return roomMapper.deleteByPrimaryKey(id);
    }


    public void insert(RoomVO roomVO) {
        Room room = new Room();
        room.setDescription(roomVO.getDescription());
        room.setId(roomVO.getId());
        room.setName(roomVO.getName());
        roomMapper.insert(room);
        room = roomMapper.selectSelective(room);
        FloorRoom floorRoom = new FloorRoom();
        floorRoom.setRoom_id(room.getId());
        floorRoom.setFloor_id(roomVO.getFloor_id());


        floorRoomMapper.insert(floorRoom);
    }


    public int insertSelective(Room record) {
        return roomMapper.insertSelective(record);
    }


    public Room selectByPrimaryKey(Integer id) {
        return roomMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Room record) {
        return roomMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Room record) {
        return roomMapper.updateByPrimaryKey(record);
    }


    public int updateBatchSelective(List<Room> list) {
        return roomMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<Room> list) {
        return roomMapper.batchInsert(list);
    }

    public List<RoomVO> list() {
        return roomMapper.list();
    }

    public RoomVO selectById(Integer roomId, Integer floorId) {
        return roomMapper.selectById(roomId,floorId);
    }

    public void update(RoomVO roomVO) {
        Room room = new Room();
        room.setDescription(roomVO.getDescription());
        room.setId(roomVO.getId());
        room.setName(roomVO.getName());


        FloorRoom floorRoom = new FloorRoom();
        floorRoom.setFloor_id(roomVO.getFloor_id());
        floorRoom.setRoom_id(room.getId());


        floorRoomMapper.update(floorRoom);
        roomMapper.updateByPrimaryKeySelective(room);
    }

    public List<Floor> listFloor() {
        return floorMapper.list();
    }
}


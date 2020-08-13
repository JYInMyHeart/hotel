package com.alex.mapper;

import com.alex.bean.Room;
import java.util.List;

import com.alex.bean.RoomVO;
import org.apache.ibatis.annotations.Param;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    int updateBatchSelective(List<Room> list);

    int batchInsert(@Param("list") List<Room> list);

    List<RoomVO> list();

    RoomVO selectById(@Param("roomId") Integer roomId, @Param("floorId") Integer floorId);

    Room selectSelective(Room room);

    Integer count();
}
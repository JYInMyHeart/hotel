package com.alex.mapper;

import com.alex.bean.FloorRoom;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FloorRoomMapper {
    int insert(FloorRoom record);

    int insertSelective(FloorRoom record);

    int batchInsert(@Param("list") List<FloorRoom> list);

    void delete(@Param("roomId") Integer roomId, @Param("floorId") Integer floorId);

    void update(FloorRoom floorRoom);
}
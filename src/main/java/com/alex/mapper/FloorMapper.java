package com.alex.mapper;

import com.alex.bean.Floor;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FloorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Floor record);

    int insertSelective(Floor record);

    Floor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);

    int updateBatchSelective(List<Floor> list);

    int batchInsert(@Param("list") List<Floor> list);

    List<Floor> list();

    Integer count();
}
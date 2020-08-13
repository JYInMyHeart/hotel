package com.alex.mapper;

import com.alex.bean.Declare;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeclareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Declare record);

    int insertSelective(Declare record);

    Declare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Declare record);

    int updateByPrimaryKey(Declare record);

    int updateBatchSelective(List<Declare> list);

    int batchInsert(@Param("list") List<Declare> list);

    List<Declare> list();

    Integer count();
}
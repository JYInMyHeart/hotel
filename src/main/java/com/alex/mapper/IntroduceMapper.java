package com.alex.mapper;

import com.alex.bean.Introduce;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntroduceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Introduce record);

    int insertSelective(Introduce record);

    Introduce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Introduce record);

    int updateByPrimaryKey(Introduce record);

    int updateBatchSelective(List<Introduce> list);

    int batchInsert(@Param("list") List<Introduce> list);

    List<Introduce> list();

    Integer count();
}
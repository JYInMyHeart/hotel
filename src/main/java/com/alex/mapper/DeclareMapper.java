package com.alex.mapper;

import com.alex.bean.Declare;
import java.util.List;
import java.util.Map;

import com.alex.service.BaseService;
import org.apache.ibatis.annotations.Param;

public interface DeclareMapper extends BaseService<Declare> {
    int deleteByPrimaryKey(Integer id);

    int insert(Declare record);

    int insertSelective(Declare record);

    Declare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Declare record);

    int updateByPrimaryKey(Declare record);

    int updateBatchSelective(List<Declare> list);

    int batchInsert(@Param("list") List<Declare> list);

    List<Declare> list();

    Integer countAll();


}
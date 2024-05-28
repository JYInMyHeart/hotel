package com.alex.mapper;

import com.alex.bean.Notice;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    int updateBatchSelective(List<Notice> list);

    int batchInsert(@Param("list") List<Notice> list);

    List<Notice> list();

    int count();
}
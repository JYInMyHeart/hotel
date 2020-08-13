package com.alex.mapper;

import com.alex.bean.Picture;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    int updateBatchSelective(List<Picture> list);

    int batchInsert(@Param("list") List<Picture> list);

    List<Picture> list();

    Integer count();
}
package com.alex.mapper;

import com.alex.bean.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateBatchSelective(List<User> list);

    int batchInsert(@Param("list") List<User> list);

    List<User> querySelective(User user);

    List<User> list();

    User selectUserByIdentityID(String identity_id);

    Integer count();
}
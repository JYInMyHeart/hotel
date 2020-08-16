package com.alex.service;

import com.alex.bean.User;
import com.alex.mapper.DeclareMapper;
import com.alex.mapper.FloorMapper;
import com.alex.mapper.IntroduceMapper;
import com.alex.mapper.NoticeMapper;
import com.alex.mapper.PictureMapper;
import com.alex.mapper.RoomMapper;
import com.alex.mapper.UserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PictureMapper pictureMapper;

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private FloorMapper floorMapper;

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private IntroduceMapper introduceMapper;

    @Resource
    private DeclareMapper declareMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }


    public int updateBatchSelective(List<User> list) {
        return userMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }

    public User querySelective(User user) {
        List<User> userList = userMapper.querySelective(user);
        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    public void deleteById(List<Integer> collect) {
        userMapper.updateBatchSelective(
                collect.stream().map(id -> userMapper.selectByPrimaryKey(id)).peek(u -> {
                    u.setActive(2);
                    u.setDelete_time(new Date());
                })
                       .collect(Collectors.toList()));
    }


    public List<User> list() {
        return userMapper.list();
    }

    public Map<String, Integer> count() {
        Map<String,Integer> map = new HashMap<>();
        map.put("user",userMapper.count());
        map.put("notice",noticeMapper.count());
        map.put("introduce",introduceMapper.count());
        map.put("room",roomMapper.count());
        map.put("floor",floorMapper.count());
        map.put("picture",pictureMapper.count());
        map.put("declare",declareMapper.countAll());
        return map;
    }

    public User selectUserByIdentityID(String identity_id) {
        return userMapper.selectUserByIdentityID(identity_id);
    }
}

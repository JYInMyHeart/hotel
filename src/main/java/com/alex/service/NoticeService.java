package com.alex.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.alex.mapper.NoticeMapper;

import java.util.Date;
import java.util.List;
import com.alex.bean.Notice;
@Service
public class NoticeService{

    @Resource
    private NoticeMapper noticeMapper;

    
    public void deleteByPrimaryKey(Integer id) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        notice.setDeleted(1);
        notice.setDelete_time(new Date());
        noticeMapper.updateByPrimaryKey(notice);
    }

    
    public int insert(Notice record) {
        return noticeMapper.insert(record);
    }

    
    public int insertSelective(Notice record) {
        return noticeMapper.insertSelective(record);
    }

    
    public Notice selectByPrimaryKey(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Notice record) {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Notice record) {
        return noticeMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatchSelective(List<Notice> list) {
        return noticeMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Notice> list) {
        return noticeMapper.batchInsert(list);
    }

    public List<Notice> list() {
        return noticeMapper.list();
    }

    public int count() {
        return noticeMapper.count();
    }
}

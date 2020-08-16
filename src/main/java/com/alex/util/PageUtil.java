package com.alex.util;

import com.alex.bean.PageBean;
import com.alex.service.BaseService;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: alex
 * @File: PageUtil
 * @Time: 11:06 2020/6/9
 */
public class PageUtil<A, T extends BaseService<A>> {


    private T dao;

    public PageUtil(T dao) {
        this.dao = dao;
    }

    public PageBean<A> helper(int page, int size, String sort, String asc, String keyword) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<A> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);
        //封装总记录数
        int totalCount = dao.count(keyword);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double num = Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);
        map.put("PageStart", (page - 1) * size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);
        map.put("keyword", keyword == null? "" : keyword);
        List<A> list = dao.listByKeyword(map);
        pageBean.setLists(list);
        return pageBean;
    }
}

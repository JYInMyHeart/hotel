package com.alex.bean;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    private int currPage;//当前页数
    private int pageSize;//每页显示的记录数
    private int pageStart;//开始查询的条数
    private String pageSort;//排序字段
    private String pageAsc;//排序规则
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> lists;//每页的显示的数据



}

package com.alex.util;


import com.alex.bean.PageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseEntity {
    

    //返回的信息
    private final String msg;
    //返回的状态码
    private final int code;
    //返回的时间
    private final String timestamp;

    private int count;

    private final Map<String, Object> data = new HashMap<>();

    public static ResponseEntity logo(String s) {
        ResponseEntity responseEntity = new ResponseEntity(0, "", MyUtil.getTime());
        responseEntity.putDataValue("src",s);
        return responseEntity;
    }

    public static ResponseEntity failed(String msg) {
        return new ResponseEntity(201, msg, MyUtil.getTime());

    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseEntity putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }



    private ResponseEntity(int code, String msg, String timestamp) {
        this.code = code;
        this.msg = msg;
        //返回的时间
        this.timestamp = timestamp;
    }



    public static <T> ResponseEntity data(List<T> list) {
        ResponseEntity responseEntity = new ResponseEntity(200, "", MyUtil.getTime());
        responseEntity.putDataValue("records",list);
        responseEntity.setCount(list.size());
        return responseEntity;
    }

    public static <T> ResponseEntity data(PageBean<T> pageBean) {
        ResponseEntity responseEntity = new ResponseEntity(200, "", MyUtil.getTime());
        responseEntity.putDataValue("records",pageBean);
        responseEntity.setCount(pageBean.getLists().size());
        return responseEntity;
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(200, "Ok", MyUtil.getTime());
    }

    public static ResponseEntity notFound() {
        return new ResponseEntity(404, "Not Found", MyUtil.getTime());
    }

    public static ResponseEntity badRequest() {
        return new ResponseEntity(400, "Bad Request", MyUtil.getTime());
    }

    public static ResponseEntity forbidden() {
        return new ResponseEntity(403, "Forbidden", MyUtil.getTime());
    }

    public static ResponseEntity unauthorized() {
        return new ResponseEntity(401, "unauthorized", MyUtil.getTime());
    }

    public static ResponseEntity serverInternalError() {
        return new ResponseEntity(500, "Server Internal Error", MyUtil.getTime());
    }

    public static ResponseEntity customerError() {
        return new ResponseEntity(1001, "Customer Error", MyUtil.getTime());
    }

    public static ResponseEntity logoutError() {
        return new ResponseEntity(1003, "logout Error", MyUtil.getTime());
    }

    public static ResponseEntity logoutSuccess() {
        return new ResponseEntity(1002, "logout Success", MyUtil.getTime());
    }
}

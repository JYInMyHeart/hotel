package com.alex.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="用户管理")
@Data
public class User implements Serializable {
    /**
    * 主键
    */
    @ExcelIgnore
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 用户名
    */
    @ExcelProperty("用户名")
    @ApiModelProperty(value="用户名")
    private String name;

    /**
    * 头像
    */
    @ExcelProperty("头像")
    @ApiModelProperty(value="头像")
    private String icon;

    /**
    * 密码
    */
    @ExcelProperty("密码")
    @ApiModelProperty(value="密码")
    private String pwd;

    /**
    * 用户权限 1 管理员 2 用户
    */
    @ExcelProperty("用户权限")
    @ApiModelProperty(value="用户权限")
    private Integer role;

    /**
    * 当前用户是否激活 1 激活 2 禁用
    */
    @ExcelProperty("激活状态")
    @ApiModelProperty(value="当前用户是否激活")
    private Integer active;

    /**
    * 工号
    */
    @ExcelProperty("工号")
    @ApiModelProperty(value="工号")
    private String identity_id;

    /**
    * 创建时间
    */
    @ExcelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间")
    private Date create_time;

    /**
    * 修改时间
    */
    @ExcelProperty("修改时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="修改时间")
    private Date update_time;

    /**
    * 删除时间
    */
    @ExcelProperty("删除时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="删除时间")
    private Date delete_time;
}
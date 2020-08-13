package com.alex.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="图片管理")
@Data
public class Picture {
    @ApiModelProperty(value="id")
    private Integer id;

    @ApiModelProperty(value="路径")
    private String path;

    @ApiModelProperty(value="描述")
    private String description;
}
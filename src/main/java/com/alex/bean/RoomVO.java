package com.alex.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="房间信息")
public class RoomVO {

    @ApiModelProperty(value="id")
    private Integer id;

    @ApiModelProperty(value="name")
    private String name;

    @ApiModelProperty(value="description")
    private String description;

    @ApiModelProperty(value="楼层id")
    private Integer floor_id;

    @ApiModelProperty(value="楼层名称")
    private String floor_name;
}

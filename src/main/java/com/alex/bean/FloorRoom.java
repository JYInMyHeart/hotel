package com.alex.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com-alex-bean-FloorRoom")
@Data
public class FloorRoom {
    @ApiModelProperty(value="楼层id")
    private Integer floor_id;

    @ApiModelProperty(value="房间id")
    private Integer room_id;
}
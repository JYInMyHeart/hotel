package com.alex.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com-alex-bean-Room")
@Data
public class Room {
    @ApiModelProperty(value="id")
    private Integer id;

    @ApiModelProperty(value="name")
    private String name;

    @ApiModelProperty(value="description")
    private String description;
}
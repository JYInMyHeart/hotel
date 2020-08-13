package com.alex.controller;

import com.alex.bean.Floor;
import com.alex.service.FloorService;
import com.alex.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@Api(tags = { "floor" }, value = "楼层管理")
@RequestMapping("/floor")
public class FloorController {

    @Resource
    private FloorService floorService;


    @ApiOperation("注册/添加楼层")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Floor floor) {
        floorService.insert(floor);
        return ResponseEntity.ok();
    }



    /**
     * 删除功能
     */
    @ApiOperation("删除楼层")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("floorId") Integer floorId) {
        floorService.deleteByPrimaryKey(floorId);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找楼层")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("floorId") Integer floorId) {
        Floor floor = floorService.selectByPrimaryKey(floorId);
        return ResponseEntity.data(Collections.singletonList(floor));
    }

    /**
     * 更新
     */
    @ApiOperation("修改楼层信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Floor floor) {
        floorService.updateByPrimaryKeySelective(floor);
        return ResponseEntity.ok();
    }

    @ApiOperation("楼层列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<Floor> floorList = floorService.list();
        if (floorList != null) {
            return ResponseEntity.data(floorList);
        } else {
            return ResponseEntity.ok();
        }
    }

}

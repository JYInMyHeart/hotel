package com.alex.controller;

import com.alex.bean.Floor;
import com.alex.bean.Room;
import com.alex.bean.RoomVO;
import com.alex.service.FloorRoomService;
import com.alex.service.FloorService;
import com.alex.service.RoomService;
import com.alex.util.JwtToken;
import com.alex.util.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Api(tags = { "room" }, value = "房间信息管理")
@RequestMapping("/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @Resource
    private FloorService floorService;

    @Resource
    private FloorRoomService floorRoomService;

    
    @ApiOperation("添加房间信息")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody RoomVO roomVO) {
        roomService.insert(roomVO);
        return ResponseEntity.ok();
    }


    /**
     * 删除功能
     */
    @ApiOperation("删除房间信息")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("roomId") Integer roomId,
                                 @RequestParam("floorId") Integer floorId) {
        floorRoomService.delete(roomId,floorId);
        roomService.deleteByPrimaryKey(roomId);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找房间信息")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("roomId") Integer roomId,
                                    @RequestParam("floorId") Integer floorId) {
        RoomVO room = roomService.selectById(roomId, floorId);
        return ResponseEntity.data(Collections.singletonList(room));
    }

    @ApiOperation("获取所有楼层信息")
    @GetMapping(value = "/listFloor")
    public ResponseEntity listFloor() {
        List<Floor> floorList = roomService.listFloor();
        return ResponseEntity.data(Collections.singletonList(floorList));
    }

    /**
     * 更新
     */
    @ApiOperation("修改房间信息信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody RoomVO room) {
        roomService.update(room);
        return ResponseEntity.ok();
    }

    @ApiOperation("房间信息列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<RoomVO> roomList = roomService.list();
        if (roomList != null) {
            return ResponseEntity.data(roomList);
        } else {
            return ResponseEntity.ok();
        }
    }


    
}

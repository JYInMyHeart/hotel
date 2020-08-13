package com.alex.controller;

import com.alex.bean.Notice;
import com.alex.service.NoticeService;
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
@Api(tags = { "notice" }, value = "公告管理")
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;


    @ApiOperation("注册/添加公告")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Notice notice) {
        noticeService.insert(notice);
        return ResponseEntity.ok();
    }



    /**
     * 删除功能
     */
    @ApiOperation("删除公告")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("noticeId") Integer noticeId) {
        noticeService.deleteByPrimaryKey(noticeId);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找公告")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("noticeId") Integer noticeId) {
        Notice notice = noticeService.selectByPrimaryKey(noticeId);
        return ResponseEntity.data(Collections.singletonList(notice));
    }

    /**
     * 更新
     */
    @ApiOperation("修改公告信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Notice notice) {
        noticeService.updateByPrimaryKeySelective(notice);
        return ResponseEntity.ok();
    }

    @ApiOperation("公告列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<Notice> noticeList = noticeService.list();
        if (noticeList != null) {
            return ResponseEntity.data(noticeList);
        } else {
            return ResponseEntity.ok();
        }
    }

}

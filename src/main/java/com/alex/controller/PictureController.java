package com.alex.controller;

import com.alex.bean.Picture;
import com.alex.service.PictureService;
import com.alex.util.FileUtil;
import com.alex.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@Api(tags = { "picture" }, value = "图片管理")
@RequestMapping("/picture")
public class PictureController {

    public final static String picturePrefix = "pic_";

    @Resource
    private PictureService pictureService;

    @Value("${web.upload-path}")
    private String picturePath;

    @Value("${project.path}")
    private String projectPath;


    @ApiOperation("添加图片")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Picture picture ) {
        pictureService.insert(picture);
        return ResponseEntity.ok();
    }

    @ApiOperation("upload图片")
    @PostMapping(value = "/upload")
    public ResponseEntity upload( MultipartFile file) {
        String fileName = FileUtil.uploadFile(file,picturePrefix,picturePath);
        return ResponseEntity.logo(fileName);
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除图片")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("pictureId") Integer pictureId) {
        pictureService.deleteByPrimaryKey(pictureId);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找图片")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("pictureId") Integer pictureId) {
        Picture picture = pictureService.selectByPrimaryKey(pictureId);
        return ResponseEntity.data(Collections.singletonList(picture));
    }

    /**
     * 更新
     */
    @ApiOperation("修改图片信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Picture picture) {
        pictureService.updateByPrimaryKeySelective(picture);
        return ResponseEntity.ok();
    }

    @ApiOperation("图片列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<Picture> pictureList = pictureService.list();
        if (pictureList != null) {
            return ResponseEntity.data(pictureList);
        } else {
            return ResponseEntity.ok();
        }
    }

}

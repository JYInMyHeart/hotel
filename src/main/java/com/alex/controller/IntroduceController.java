package com.alex.controller;

import com.alex.bean.Introduce;
import com.alex.service.IntroduceService;
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
@Api(tags = { "introduce" }, value = "简介管理")
@RequestMapping("/introduce")
public class IntroduceController {

    @Resource
    private IntroduceService introduceService;


    @ApiOperation("注册/添加简介")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Introduce introduce) {
        introduceService.insert(introduce);
        return ResponseEntity.ok();
    }



    /**
     * 删除功能
     */
    @ApiOperation("删除简介")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("introduceId") Integer introduceId) {
        introduceService.deleteByPrimaryKey(introduceId);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找简介")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("introduceId") Integer introduceId) {
        Introduce introduce = introduceService.selectByPrimaryKey(introduceId);
        return ResponseEntity.data(Collections.singletonList(introduce));
    }

    /**
     * 更新
     */
    @ApiOperation("修改简介信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Introduce introduce) {
        introduceService.updateByPrimaryKeySelective(introduce);
        return ResponseEntity.ok();
    }

    @ApiOperation("简介列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<Introduce> introduceList = introduceService.list();
        if (introduceList != null) {
            return ResponseEntity.data(introduceList);
        } else {
            return ResponseEntity.ok();
        }
    }

}

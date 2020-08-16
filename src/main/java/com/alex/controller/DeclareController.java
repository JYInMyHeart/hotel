package com.alex.controller;

import com.alex.bean.Declare;
import com.alex.bean.PageBean;
import com.alex.service.DeclareService;
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
@Api(tags = { "declare" }, value = "宣传管理")
@RequestMapping("/declare")
public class DeclareController {

    @Resource
    private DeclareService declareService;


    @ApiOperation("注册/添加宣传")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Declare declare) {
        declareService.insert(declare);
        return ResponseEntity.ok();
    }



    /**
     * 删除功能
     */
    @ApiOperation("删除宣传")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("declareId") Integer declareId) {
        declareService.deleteByPrimaryKey(declareId);
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找宣传")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("declareId") Integer declareId) {
        Declare declare = declareService.selectByPrimaryKey(declareId);
        return ResponseEntity.data(Collections.singletonList(declare));
    }

    /**
     * 更新
     */
    @ApiOperation("修改宣传信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Declare declare) {
        declareService.updateByPrimaryKeySelective(declare);
        return ResponseEntity.ok();
    }

    @ApiOperation("宣传列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<Declare> declareList = declareService.list();
        if (declareList != null) {
            return ResponseEntity.data(declareList);
        } else {
            return ResponseEntity.ok();
        }
    }

    @ApiOperation("宣传列表模糊查询")
    @GetMapping(value = "/listByKeyword")
    public ResponseEntity listByKeyword(@RequestParam(required = false) String keyword,
                                        @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                        @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {

        PageBean<Declare> declareList = declareService.listByKeyword(page, limit, sort, asc, keyword);
        if (declareList != null) {
            return ResponseEntity.data(declareList);
        } else {
            return ResponseEntity.ok();
        }
    }

}

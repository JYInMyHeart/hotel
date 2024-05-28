package com.alex.controller;

import com.alex.bean.User;
import com.alex.service.UserService;
import com.alex.util.FileUtil;
import com.alex.util.JwtToken;
import com.alex.util.ResponseEntity;
import com.alex.util.UploadDataListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Api(tags = { "user" }, value = "用户管理")
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        ResponseEntity responseEntity = ResponseEntity.ok();
        try {
            User u = userService.querySelective(user);
            if (u != null && u.getPwd().equals(user.getPwd())) {
                String token = JwtToken.sign(u, 60L * 1000L * 30L);
                responseEntity.putDataValue("username", u.getName());
                responseEntity.putDataValue("userAccount", u.getIdentity_id());
                responseEntity.putDataValue("token", token);
                responseEntity.putDataValue("userRole", u.getRole());
                responseEntity.putDataValue("userLogo", u.getIcon());
                responseEntity.putDataValue("userId", u.getIdentity_id());
            } else {
                responseEntity = ResponseEntity.customerError();
                responseEntity.putDataValue("msg", "账号密码错误");
            }
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            responseEntity = ResponseEntity.customerError();
            responseEntity.putDataValue("msg", "账号密码错误");
        }
        return responseEntity;
    }

    @ApiOperation("注册/添加用户")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody User userPojo) {
        if (userPojo.getRole() == null) {
            userPojo.setRole(2);
        }
        userPojo.setActive(1);
        userService.insert(userPojo);
        return ResponseEntity.ok();
    }

    @ApiOperation("检查账号")
    @GetMapping(value = "/checkName")
    public ResponseEntity checkName(@RequestParam String name) {
        User userPojo = new User();
        userPojo.setName(name);
        User user = userService.querySelective(userPojo);
        if (user == null) {
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.serverInternalError();
        }
    }

    /**
     * 删除功能
     */
    @ApiOperation("删除用户")
    @GetMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("userId") String userId) {
        userService.deleteById(Arrays.stream(userId.split(","))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList()));
        return ResponseEntity.ok();
    }

    @ApiOperation("通过id查找用户")
    @GetMapping(value = "/queryById")
    public ResponseEntity queryById(@RequestParam("userId") Integer userId) {
        User user = userService.selectByPrimaryKey(userId);
        return ResponseEntity.data(Collections.singletonList(user));
    }

    @ApiOperation("通过identity_id查找用户")
    @GetMapping(value = "/selectUserByIdentityID")
    public ResponseEntity selectUserByIdentityID(@RequestParam("identity_id") String identity_id) {
        User user = userService.selectUserByIdentityID(identity_id);
        return ResponseEntity.data(Collections.singletonList(user));
    }

    /**
     * 更新
     */
    @ApiOperation("修改用户信息")
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody User user) {
        userService.updateByPrimaryKeySelective(user);
        return ResponseEntity.ok();
    }

    @ApiOperation("用户列表")
    @GetMapping(value = "/list")
    public ResponseEntity list() {

        List<User> userList = userService.list();
        if (userList != null) {
            return ResponseEntity.data(userList);
        } else {
            return ResponseEntity.ok();
        }
    }

    @Value("${web.upload-path}")
    private String picturePath;

    @ApiOperation("用户头像上传")
    @PostMapping(value = "/upload")
    public ResponseEntity upload(MultipartFile file) {
        try {
          String fileName = FileUtil.uploadFile(file,"logo_",picturePath);
            return ResponseEntity.logo(fileName);
        } catch (Exception e) {
            logger.error("upload failed!",e);
            return ResponseEntity.serverInternalError();
        }
    }

    @ApiOperation("统计")
    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity count() throws IOException {
        ResponseEntity responseData;
        Map<String, Integer> count = userService.count();
        responseData = ResponseEntity.data(Collections.singletonList(count));
        return responseData;
    }

    @ApiOperation("导入接口")
    @PostMapping(value = "/uploadUser")
    public ResponseEntity uploadUser(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), User.class, new UploadDataListener(userService)).sheet().doRead();
            return ResponseEntity.data(Collections.singletonList("nice"));
        } catch (IOException e) {
            return ResponseEntity.serverInternalError();
        }
    }


    @ApiOperation(value = "导出", notes = "导出")
    @GetMapping("/excel")
    public void excel(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("导出数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<User> list = userService.list();
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), User.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                     .doWrite(list);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
}

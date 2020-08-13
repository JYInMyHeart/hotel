package com.alex.interceptor;

import com.alex.util.MyUtil;
import com.alex.util.ResponseEntity;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static String globalToken = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "token,content-type");
//
//        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        // 在拦截点执行前拦截，如果返回true则不执行拦截点后的操作（拦截成功）
        // 返回false则不执行拦截
        String token = request.getHeader("token");
        if (token == null || token.equals("null")) {
            return false;
        }
        globalToken = token;
        //token不存在
        if (StringUtils.isBlank(token)) {
            ResponseEntity responseData = ResponseEntity.forbidden();
            responseData.putDataValue("msg", "token不存在或已过期");
            sendDataToResponse(response, responseData);
            return false;
        }
        int value = MyUtil.judgeToken(token);
        if (value == 1) {
            ResponseEntity responseData = ResponseEntity.forbidden();
            responseData.putDataValue("msg", "token不存在或已过期");
            sendDataToResponse(response, responseData);
            return false;
        } else if (value == 2) {
            ResponseEntity responseData = ResponseEntity.forbidden();
            responseData.putDataValue("msg", "没有权限访问");
            sendDataToResponse(response, responseData);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }


    private void sendDataToResponse(HttpServletResponse response, ResponseEntity vo) throws IOException {
        //重置response
        response.reset();
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter pw = response.getWriter();

        pw.write(JSON.toJSONString(vo));

        pw.flush();
        pw.close();
    }


}

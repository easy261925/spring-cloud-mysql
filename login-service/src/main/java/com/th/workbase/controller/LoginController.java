package com.th.workbase.controller;

import com.th.workbase.entity.LoginDto;
import com.th.workbase.entity.ResponseResultDto;
import com.th.workbase.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2021-04-15-9:18
 * @Author tangJ
 * @Description 登录接口
 * @Version 1.0
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "登录接口", notes = "登录接口")
    @PostMapping("/login")
    public ResponseResultDto login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return loginService.login(loginDto, request);
    }

    @ApiOperation(value = "登出接口", notes = "登出接口")
    @PostMapping("/logout")
    public ResponseResultDto logout(HttpServletRequest request) {
        return loginService.logout(request);

    }
}

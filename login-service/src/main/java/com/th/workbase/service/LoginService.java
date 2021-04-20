package com.th.workbase.service;

import com.th.workbase.entity.LoginDto;
import com.th.workbase.entity.ResponseResultDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2021-04-16-14:56
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
public interface LoginService {
    ResponseResultDto login(LoginDto loginDto,HttpServletRequest request);

    ResponseResultDto logout(HttpServletRequest request);
}

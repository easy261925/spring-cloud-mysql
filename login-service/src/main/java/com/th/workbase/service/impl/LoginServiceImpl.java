package com.th.workbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.th.workbase.common.utils.DESUtil;
import com.th.workbase.common.utils.RedisUtil;
import com.th.workbase.config.JwtConfig;
import com.th.workbase.entity.LoginDto;
import com.th.workbase.entity.ResponseResultDto;
import com.th.workbase.entity.SysUserDto;
import com.th.workbase.mapper.SysUserMapper;
import com.th.workbase.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Date 2021-04-16-14:57
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private Environment env;


    @Override
    public ResponseResultDto login(LoginDto loginDto, HttpServletRequest request) {
        String loginPass = loginDto.getPassword();
        String loginName = loginDto.getLoginName();
        // 用户名密码不可为空
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPass)) {
            return ResponseResultDto.loginError();
        }
        // 验证用户名密码正确性
        QueryWrapper<SysUserDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        queryWrapper.eq("password", DESUtil.encrypt(env.getProperty("local.pass.des.baseKey"), loginPass));
        SysUserDto resultUser = userMapper.selectOne(queryWrapper);
        resultUser.setPassword(null);
        if (resultUser == null) {
            return ResponseResultDto.loginError();
        }
        // 刷新token 删除已存在token key结构为用户名_用户类型_机构id
        String tmp = redisUtil.get(loginName + "_" + resultUser.getOrgId() + "_" + resultUser.getId(), String.class);
        String tmpToken = loginName + "_"  + resultUser.getOrgId() + "_" + resultUser.getId();
        // 删除redis中的token信息
        if (StringUtils.isNotBlank(tmp)) {
            redisUtil.del(tmpToken);
            redisUtil.del(tmp);
        }
        // 生成新token 保存到redis中
        String token = jwtConfig.getToken(loginName  + "_" + resultUser.getOrgId() + "_" + resultUser.getId());
        redisUtil.set(loginName + "_" + resultUser.getOrgId() + "_" + resultUser.getId(), token);
        redisUtil.set(token, loginName + "_" + resultUser.getOrgId() + "_" + resultUser.getId());
        // 刷新登录时间
        resultUser.setLoginDate(new Date());
        // 刷新登录地址
        String remoteAddr = request.getRemoteAddr();
        resultUser.setLoginIp(remoteAddr);
        userMapper.updateById(resultUser);

        return ResponseResultDto.ok().data("user", resultUser).data(jwtConfig.getHeader(), token);
    }

    @Override
    public ResponseResultDto logout(HttpServletRequest request) {
        try {
            // 获取token
            String token = request.getHeader(jwtConfig.getHeader());
            String tmp = redisUtil.get(token, String.class);
            // 注销票据
            redisUtil.del(token);
            redisUtil.del(tmp);
            return ResponseResultDto.ok();
        } catch (Exception e) {
            return ResponseResultDto.ServiceError("退出失败");
        }
    }
}

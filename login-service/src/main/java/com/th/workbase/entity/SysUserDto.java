package com.th.workbase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @author tangjian
 * @since 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysUser对象")
@TableName("sys_user")
public class SysUserDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String userName;

    @ApiModelProperty(value = "组织id")
    private String orgId;

    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;


}

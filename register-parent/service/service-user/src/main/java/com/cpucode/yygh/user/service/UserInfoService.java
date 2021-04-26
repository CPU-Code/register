package com.cpucode.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.user.UserInfo;
import com.cpucode.yygh.vo.user.LoginVo;

import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/25
 * @time : 23:13
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    Map<String, Object> login(LoginVo loginVo);

}

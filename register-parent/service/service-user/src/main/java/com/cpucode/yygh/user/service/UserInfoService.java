package com.cpucode.yygh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.user.UserInfo;
import com.cpucode.yygh.vo.user.LoginVo;
import com.cpucode.yygh.vo.user.UserAuthVo;
import com.cpucode.yygh.vo.user.UserInfoQueryVo;

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
    Map<String, Object> loginUser(LoginVo loginVo);

    /**
     * 根据openid判断
     * @param openid
     * @return
     */
    UserInfo selectWxInfoOpenId(String openid);

    /**
     * 用户认证
     * @param userId
     * @param userAuthVo
     */
    void userAuth(Long userId, UserAuthVo userAuthVo);

    /**
     * 用户列表（条件查询带分页）
     * @param pageParam
     * @param userInfoQueryVo
     * @return
     */
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    /**
     * 用户锁定
     * @param userId
     * @param status 0：锁定 1：正常
     */
    void lock(Long userId, Integer status);
}

package com.cpucode.yygh.common.utils;

import com.cpucode.yygh.common.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前用户信息工具类
 *
 * @author : cpucode
 * @date : 2021/5/5
 * @time : 13:08
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public class AuthContextHolder {

    /**
     * 获取当前用户id
     * @param request
     * @return
     */
    public static Long getUserId(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");

        //jwt从token获取userid
        Long userId = JwtHelper.getUserId(token);
    }

    /**
     * 获取当前用户名称
     * @param request
     * @return
     */
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");

        //jwt从token获取userid
        String userName = JwtHelper.getUserName(token);

        return userName;
    }
}

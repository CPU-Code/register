package com.cpucode.yygh.user.api;

import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.common.utils.AuthContextHolder;
import com.cpucode.yygh.model.user.UserInfo;
import com.cpucode.yygh.user.service.UserInfoService;
import com.cpucode.yygh.vo.user.LoginVo;
import com.cpucode.yygh.vo.user.UserAuthVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/26
 * @time : 10:42
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户手机号登录接口
     * @param loginVo
     * @return
     */
    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        Map<String, Object> info = userInfoService.loginUser(loginVo);

        return Result.ok(info);
    }

    /**
     * 用户认证接口
     * @param userAuthVo
     * @param request
     * @return
     */
    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        userInfoService.userAuth(AuthContextHolder.getUserId(request), userAuthVo);

        return Result.ok();
    }

    /**
     * 获取用户id信息接口
     * @param request
     * @return
     */
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        //获取当前用户id
        Long userId = AuthContextHolder.getUserId(request);
        //根据 ID 查询用户信息
        UserInfo userInfo = userInfoService.getById(userId);

        return Result.ok(userInfo);
    }

}

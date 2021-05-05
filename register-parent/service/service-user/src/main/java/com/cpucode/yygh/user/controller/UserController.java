package com.cpucode.yygh.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.model.user.UserInfo;
import com.cpucode.yygh.user.service.UserInfoService;
import com.cpucode.yygh.vo.user.LoginVo;
import com.cpucode.yygh.vo.user.UserInfoQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/26
 * @time : 9:36
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户列表（条件查询带分页）
     *
     * @param page
     * @param limit
     * @param userInfoQueryVo
     * @return
     */
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       UserInfoQueryVo userInfoQueryVo) {
        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel =
                userInfoService.selectPage(pageParam, userInfoQueryVo);

        return Result.ok(pageModel);
    }

    /**
     * 用户锁定
     *
     * @param userId
     * @param status
     * @return
     */
    @ApiOperation(value = "锁定")
    @GetMapping("lock/{userId}/{status}")
    public Result lock(@PathVariable Long userId,
                       @PathVariable Integer status) {
        userInfoService.lock(userId, status);

        return Result.ok();
    }

    /**
     * 用户详情
     * @param userId
     * @return
     */
    @GetMapping("show/{userId}")
    public Result show(@PathVariable Long userId) {
        Map<String, Object> map = userInfoService.show(userId);

        return Result.ok(map);
    }
}
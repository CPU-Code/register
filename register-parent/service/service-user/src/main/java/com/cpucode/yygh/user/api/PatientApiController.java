package com.cpucode.yygh.user.api;

import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.common.utils.AuthContextHolder;
import com.cpucode.yygh.model.user.Patient;
import com.cpucode.yygh.user.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 就诊人管理接口
 * @author : cpucode
 * @date : 2021/5/5
 * @time : 16:23
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/api/user/patient")
public class PatientApiController {
    @Autowired
    private PatientService patientService;

    /**
     * 获取就诊人列表
     * @param request
     * @return
     */
    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        List<Patient> list = patientService.findAllUserId(userId);

        return Result.ok(list);
    }
}

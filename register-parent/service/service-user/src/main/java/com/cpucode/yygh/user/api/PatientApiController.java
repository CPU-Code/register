package com.cpucode.yygh.user.api;

import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.common.utils.AuthContextHolder;
import com.cpucode.yygh.model.user.Patient;
import com.cpucode.yygh.user.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加就诊人
     * @param patient
     * @param request
     * @return
     */
    @PostMapping("auth/save")
    public Result savePatient(@RequestBody Patient patient, HttpServletRequest request){
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);

        return Result.ok();
    }

    /**
     * 根据id获取就诊人信息
     * @param id
     * @return
     */
    @GetMapping("auth/get/{id}")
    public Result getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientId(id);

        return Result.ok(patient);
    }

    /**
     * 修改就诊人
     * @param patient
     * @return
     */
    @PostMapping("auth/update")
    public Result updatePatient(@RequestBody Patient patient) {
        patientService.updateById(patient);

        return Result.ok();
    }

    /**
     * 删除就诊人
     * @param id
     * @return
     */
    @DeleteMapping("auth/remove/{id}")
    public Result removePatient(@PathVariable Long id) {
        patientService.removeById(id);

        return Result.ok();
    }

    /**
     * 根据就诊人id获取就诊人信息
     * @param id
     * @return
     */
    @GetMapping("inner/get/{id}")
    public Patient getPatientOrder(@PathVariable Long id) {
        Patient patient = patientService.getPatientId(id);

        return patient;
    }

}

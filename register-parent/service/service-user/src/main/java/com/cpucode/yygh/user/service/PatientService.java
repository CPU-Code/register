package com.cpucode.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.user.Patient;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/5/5
 * @time : 16:15
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface PatientService extends IService<Patient> {
    /**
     * 获取就诊人列表
     * @param userId
     * @return
     */
    List<Patient> findAllUserId(Long userId);

    /**
     * 根据id获取就诊人信息
     * @param id
     * @return
     */
    Patient getPatientId(Long id);

}

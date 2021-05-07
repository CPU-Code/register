package com.cpucode.yygh.user.client;

import com.cpucode.yygh.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : cpucode
 * @date : 2021/5/7
 * @time : 14:52
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {

    /**
     * 根据就诊人id获取就诊人信息
     * @param id
     * @return
     */
    @GetMapping("/api/user/patient/inner/get/{id}")
    public Patient getPatientOrder(@PathVariable("id") Long id);
}

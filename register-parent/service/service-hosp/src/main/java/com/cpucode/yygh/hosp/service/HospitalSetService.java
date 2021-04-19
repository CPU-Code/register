package com.cpucode.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.hosp.HospitalSet;

/**
 * @author : cpucode
 * @date : 2021/4/13
 * @time : 17:19
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface HospitalSetService extends IService<HospitalSet> {
    /**
     * 获取签名key
     * @param hoscode
     * @return
     */
    String getSignKey(String hoscode);
}

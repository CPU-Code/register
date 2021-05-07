package com.cpucode.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.hosp.HospitalSet;
import com.cpucode.yygh.vo.order.SignInfoVo;

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

    /**
     * 获取医院签名信息
     * @param hoscode
     * @return
     */
    SignInfoVo getSignInfoVo(String hoscode);
}

package com.cpucode.yygh.msm.service;

import com.cpucode.yygh.vo.msm.MsmVo;

/**
 * @author : cpucode
 * @date : 2021/4/26
 * @time : 11:45
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface MsmService {
    /**
     * 发送手机验证码
     * @param phone
     * @param code
     * @return
     */
    boolean send(String phone, String code);

    /**
     * mq使用发送短信
     * @param msmVo
     * @return
     */
    boolean send(MsmVo msmVo);
}

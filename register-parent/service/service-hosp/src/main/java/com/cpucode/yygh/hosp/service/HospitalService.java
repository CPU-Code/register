package com.cpucode.yygh.hosp.service;

import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/17
 * @time : 23:02
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface HospitalService {
    /**
     * 上传医院信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);
}

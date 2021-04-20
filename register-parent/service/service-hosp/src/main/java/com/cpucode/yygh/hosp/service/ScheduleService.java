package com.cpucode.yygh.hosp.service;

import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/20
 * @time : 18:02
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface ScheduleService {
    /**
     * 上传排班信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);
}

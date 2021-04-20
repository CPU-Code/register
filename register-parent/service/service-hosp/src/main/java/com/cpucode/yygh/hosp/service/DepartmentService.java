package com.cpucode.yygh.hosp.service;

import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/20
 * @time : 14:09
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface DepartmentService {
    /**
     * 上传科室信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);
}

package com.cpucode.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : cpucode
 * @date : 2021/4/13
 * @time : 17:33
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Configuration
@MapperScan("com.cpucode.yygh.hosp.mapper")
public class HospConfig {
}

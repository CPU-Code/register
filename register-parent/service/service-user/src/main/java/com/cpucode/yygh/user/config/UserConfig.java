package com.cpucode.yygh.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : cpucode
 * @date : 2021/4/26
 * @time : 9:35
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Configuration
@MapperScan("com.cpucode.yygh.user.mapper")
public class UserConfig {
}

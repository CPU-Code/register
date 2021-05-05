package com.cpucode.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : cpucode
 * @date : 2021/4/27
 * @time : 22:51
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface FileService {
    /**
     * 上传文件到阿里云oss
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}

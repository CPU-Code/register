package com.cpucode.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.cmn.Dict;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/4/15
 * @time : 13:53
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface DictService extends IService<Dict> {
    /**
     * 根据数据id查询子数据列表
     */
    List<Dict> findChlidData(Long id);
}

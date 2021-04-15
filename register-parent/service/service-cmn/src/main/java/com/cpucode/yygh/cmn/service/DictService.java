package com.cpucode.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpucode.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 导出数据字典接口
     * @param response
     */
    void exportData(HttpServletResponse response);

    /**
     * 导入数据字典
     * @param file
     */
    void importDictData(MultipartFile file);
}

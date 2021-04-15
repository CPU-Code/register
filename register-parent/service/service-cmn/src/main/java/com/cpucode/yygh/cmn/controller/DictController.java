package com.cpucode.yygh.cmn.controller;

import com.cpucode.yygh.cmn.service.DictService;
import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/4/15
 * @time : 13:51
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Api(description = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 根据数据id查询子数据列表
     * @param id
     * @return
     */
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChlidData(id);

        return Result.ok(list);
    }
}

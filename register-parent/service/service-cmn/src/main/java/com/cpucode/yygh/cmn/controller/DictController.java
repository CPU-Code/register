package com.cpucode.yygh.cmn.controller;

import com.cpucode.yygh.cmn.service.DictService;
import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
//@CrossOrigin
public class DictController {
    @Autowired
    private DictService dictService;

    /**
     * 导入数据字典
     * @param file
     * @return
     */
    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importDictData(file);

        return Result.ok();
    }


    /**
     * 导出数据字典接口
     * @param response
     */
    @ApiOperation(value = "导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }


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

    /**
     * 获取数据字典名称
     * @param dictCode
     * @param value
     * @return
     */
    @ApiOperation(value = "获取数据字典名称")
    @GetMapping(value = "/getName/{dictCode}/{value}")
    public String getName(
            @ApiParam(name = "dictCode", value = "上级编码", required = true)
            @PathVariable("dictCode") String dictCode,

            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value) {

        String dictName = dictService.getDictName(dictCode,value);

        return dictName;
    }

    /**
     * 根据value查询
     * @param value
     * @return
     */
    @ApiOperation(value = "获取数据字典名称")
    @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/getName/{value}")
    public String getName(
            @ApiParam(name = "value", value = "值", required = true)
            @PathVariable("value") String value) {
        return dictService.getDictName("", value);
    }

    /**
     * 根据dictCode获取下级节点
     * @param dictCode
     * @return
     */
    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping(value = "/findByDictCode/{dictCode}")
    public Result<List<Dict>> findByDictCode(
            @ApiParam(name = "dictCode", value = "节点编码", required = true)
            @PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);

        return Result.ok(list);
    }


}

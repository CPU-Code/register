package com.cpucode.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.hosp.service.HospitalSetService;
import com.cpucode.yygh.model.hosp.HospitalSet;
import com.cpucode.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/4/13
 * @time : 17:22
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    /**
     * 注入service
     */
    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 查询医院设置表所有信息
     * http://localhost:8201/admin/hosp/hospitalSet/findAll
     *
     * @return
     */
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();

        return Result.ok(list);
    }

    /**
     * 逻辑删除医院设置
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);

        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 条件查询带分页
     * @param current   传递当前页
     * @param limit     每页记录数
     * @param hospitalSetQueryVo
     * @return
     */
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);

        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();

        //医院名称
        String hosname = hospitalSetQueryVo.getHosname();
        //医院编号
        String hoscode = hospitalSetQueryVo.getHoscode();

        if(!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname", hosname);
        }
        if(!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode", hoscode);
        }

        //调用方法实现分页查询
        IPage<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        //返回结果
        return Result.ok(pageHospitalSet);

    }

}

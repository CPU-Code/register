package com.cpucode.yygh.hosp.controller.api;

import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.hosp.service.DepartmentService;
import com.cpucode.yygh.hosp.service.HospitalService;
import com.cpucode.yygh.model.hosp.Hospital;
import com.cpucode.yygh.vo.hosp.DepartmentVo;
import com.cpucode.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/24
 * @time : 16:19
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/api/hosp/hospital")
public class HospitalApiController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询医院列表
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @ApiOperation(value = "查询医院列表")
    @GetMapping("findHospList/{page}/{limit}")
    public Result findHospList(@PathVariable Integer page,
                               @PathVariable Integer limit,
                               HospitalQueryVo hospitalQueryVo) {
        //显示上线的医院
        //hospitalQueryVo.setStatus(1);

        Page<Hospital> hospitals = hospitalService.selectHospPage(page, limit, hospitalQueryVo);

        return Result.ok(hospitals);
    }

    /**
     * 根据医院名称获取医院列表
     * @param hosname
     * @return
     */
    @ApiOperation(value = "根据医院名称获取医院列表")
    @GetMapping("findByHosname/{hosname}")
    public Result findByHosname(
            @ApiParam(name = "hosname", value = "医院名称", required = true)
            @PathVariable String hosname) {

        return Result.ok(hospitalService.findByHosname(hosname));
    }

    /**
     * 根据医院编号获取科室
     * @param hoscode
     * @return
     */
    @ApiOperation(value = "根据医院编号获取科室")
    @GetMapping("department/{hoscode}")
    public Result department(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);

        return Result.ok(list);
    }

    /**
     * 医院预约挂号详情
     * @param hoscode
     * @return
     */
    @ApiOperation(value = "医院预约挂号详情")
    @GetMapping("findHospDetail/{hoscode}")
    public Result findHospDetail(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode) {
        Map<String, Object> map = hospitalService.item(hoscode);

        return Result.ok(map);
    }


}

package com.cpucode.yygh.hosp.controller;

import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.hosp.service.ScheduleService;
import com.cpucode.yygh.model.hosp.Schedule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/22
 * @time : 16:10
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Api(tags = "排班")
@RestController
@RequestMapping("/admin/hosp/schedule")
//@CrossOrigin
public class ScheduleController {
    /**
     * 注入service
     */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 根据医院编号 和 科室编号 ，查询排班规则数据
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    @ApiOperation(value ="查询排班规则数据")
    @GetMapping("getScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result getScheduleRule(@PathVariable long page,
                                  @PathVariable long limit,
                                  @PathVariable String hoscode,
                                  @PathVariable String depcode) {
        Map<String,Object> map = scheduleService.getRuleSchedule(page,limit,hoscode,depcode);

        return Result.ok(map);
    }

    /**
     * 根据医院编号 、科室编号和工作日期，查询排班详细信息
     * @param hoscode
     * @param depcode
     * @param workDate
     * @return
     */
    @ApiOperation(value = "查询排班详细信息")
    @GetMapping("getScheduleDetail/{hoscode}/{depcode}/{workDate}")
    public Result getScheduleDetail( @PathVariable String hoscode,
                                     @PathVariable String depcode,
                                     @PathVariable String workDate) {
        List<Schedule> list = scheduleService.getDetailSchedule(hoscode,depcode,workDate);

        return Result.ok(list);
    }


}

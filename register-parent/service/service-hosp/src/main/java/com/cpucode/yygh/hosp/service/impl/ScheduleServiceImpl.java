package com.cpucode.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cpucode.yygh.hosp.repository.ScheduleRepository;
import com.cpucode.yygh.hosp.service.ScheduleService;
import com.cpucode.yygh.model.hosp.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/20
 * @time : 18:03
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    /**
     * 上传排班信息
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        //paramMap 转换department对象
        Schedule schedule = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Schedule.class);

        //根据医院编号 和 排班编号查询
        Schedule targetSchedule = scheduleRepository.
                getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());

        //判断
        if(null != targetSchedule) {
        //值copy不为null的值，该方法为自定义方法
            targetSchedule.setUpdateTime(new Date());
            targetSchedule.setIsDeleted(0);
            targetSchedule.setStatus(1);

            scheduleRepository.save(targetSchedule);
        } else {
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setIsDeleted(0);
            schedule.setStatus(1);

            scheduleRepository.save(schedule);
        }
    }

}

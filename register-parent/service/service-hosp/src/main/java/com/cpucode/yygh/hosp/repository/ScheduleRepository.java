package com.cpucode.yygh.hosp.repository;

import com.cpucode.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : cpucode
 * @date : 2021/4/20
 * @time : 18:02
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    /**
     * 根据医院编号 和 排班编号查询
     * @param hoscode
     * @param hosScheduleId
     * @return
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}

package com.cpucode.yygh.hosp.repository;

import com.cpucode.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : cpucode
 * @date : 2021/4/20
 * @time : 14:08
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    /**
     * 上传科室接口
     * @param hoscode
     * @param depcode
     * @return
     */
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}

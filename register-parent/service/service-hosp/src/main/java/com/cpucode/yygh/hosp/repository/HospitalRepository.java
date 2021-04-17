package com.cpucode.yygh.hosp.repository;

import com.cpucode.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : cpucode
 * @date : 2021/4/17
 * @time : 23:01
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
}

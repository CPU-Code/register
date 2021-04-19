package com.cpucode.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cpucode.yygh.hosp.repository.HospitalRepository;
import com.cpucode.yygh.hosp.service.HospitalService;
import com.cpucode.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/17
 * @time : 23:02
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    /**
     *
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        //log.info(JSONObject.toJSONString(paramMap));

        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap),Hospital.class);

        //判断是否存在
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        if(null != targetHospital) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            //0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }


}

package com.cpucode.yygh.hosp.controller;

import com.cpucode.yygh.hosp.service.HospitalSetService;
import com.cpucode.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/4/13
 * @time : 17:22
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
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
     *   http://localhost:8201/admin/hosp/hospitalSet/findAll
     * @return
     */
    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();

        return list;
    }
}

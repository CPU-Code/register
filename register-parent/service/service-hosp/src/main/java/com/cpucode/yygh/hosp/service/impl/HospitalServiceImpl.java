package com.cpucode.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cpucode.yygh.cmn.client.DictFeignClient;
import com.cpucode.yygh.hosp.repository.HospitalRepository;
import com.cpucode.yygh.hosp.service.HospitalService;
import com.cpucode.yygh.model.hosp.Hospital;
import com.cpucode.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private DictFeignClient dictFeignClient;


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

    /**
     *
     * @param hoscode
     * @return
     */
    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    /**
     * 医院列表(条件查询分页)
     * @param page
     * @param limit
     * @param hospitalQueryVo
     * @return
     */
    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        //创建pageable对象
        Pageable pageable = PageRequest.of(page - 1, limit);

        //hospitalSetQueryVo转换Hospital对象
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);

        //创建匹配器，即如何使用查询条件
        //构建对象
        //改变默认字符串匹配方式：模糊查询
        //改变默认大小写忽略方式：忽略大小写
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        //创建实例
        Example<Hospital> example = Example.of(hospital, matcher);
        //调用方法实现查询
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);

        //获取查询list集合，遍历进行医院等级封装
        pages.getContent().stream().forEach(item -> {
            this.setHospitalHosType(item);
        });


        return pages;
    }

    /**
     * 获取查询list集合，遍历进行医院等级封装
     * @param hospital
     * @return
     */
    private Hospital setHospitalHosType(Hospital hospital) {
        //根据dictCode和value获取医院等级名称
        String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());

        //查询省 市  地区
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("fullAddress", provinceString + cityString + districtString);
        hospital.getParam().put("hostypeString", hostypeString);

        return hospital;

    }

    /**
     * 更新医院上线状态
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(String id, Integer status) {
        if(status.intValue() == 0 || status.intValue() == 1) {
            //根据id查询医院信息
            Hospital hospital = hospitalRepository.findById(id).get();

            //设置修改的值
            hospital.setStatus(status);
            hospital.setUpdateTime(new Date());

            hospitalRepository.save(hospital);
        }
    }

    /**
     * 医院详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getHospById(String id) {
        Map<String, Object> result = new HashMap<>();

        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());

        //医院基本信息（包含医院等级）
        result.put("hospital", hospital);

        //单独处理更直观
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);

        return result;
    }

    /**
     * 根据医院编号获取医院名称
     * @param hoscode
     * @return
     */
    @Override
    public String getHospName(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);

        if(null != hospital) {
            return hospital.getHosname();
        }

        return "";
    }



}

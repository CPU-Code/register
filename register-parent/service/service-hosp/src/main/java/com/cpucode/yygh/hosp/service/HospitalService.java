package com.cpucode.yygh.hosp.service;

import com.cpucode.yygh.model.hosp.Hospital;
import com.cpucode.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/17
 * @time : 23:02
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
public interface HospitalService {
    /**
     * 上传医院信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 实现根据医院编号查询
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);

    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页记录数
     * @param hospitalQueryVo 查询条件
     * @return
     */
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新医院上线状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 医院详情信息
     * @param id
     * @return
     */
    Map<String, Object> getHospById(String id);

    /**
     * 根据医院编号获取医院名称接口
     * @param hoscode
     * @return
     */
    String getHospName(String hoscode);

    /**
     * 根据医院名称获取医院列表
     */
    List<Hospital> findByHosname(String hosname);
}

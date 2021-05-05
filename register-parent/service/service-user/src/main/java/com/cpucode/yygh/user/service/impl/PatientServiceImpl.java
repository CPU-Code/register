package com.cpucode.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.yygh.cmn.client.DictFeignClient;
import com.cpucode.yygh.enums.DictEnum;
import com.cpucode.yygh.model.user.Patient;
import com.cpucode.yygh.user.mapper.PatientMapper;
import com.cpucode.yygh.user.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/5/5
 * @time : 16:17
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
    @Autowired
    private DictFeignClient dictFeignClient;

    /**
     * 获取就诊人列表
     * @param userId
     * @return
     */
    @Override
    public List<Patient> findAllUserId(Long userId) {
        //根据userid查询所有就诊人信息列表
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Patient> patientList = baseMapper.selectList(wrapper);

        //通过远程调用，得到编码对应具体内容，查询数据字典表内容
        patientList.stream().forEach(item -> {
            //其他参数封装
            this.packPatient(item);
        });

        return patientList;
    }

    /**
     * 根据id获取就诊人信息
     * @param id
     * @return
     */
    @Override
    public Patient getPatientId(Long id) {

        return this.packPatient(baseMapper.selectById(id));
    }

    /**
     * Patient对象里面其他参数封装
     * @param patient
     * @return
     */
    private Patient packPatient(Patient patient) {
        //根据证件类型编码，获取证件类型具体指
        //联系人证件
        String certificatesTypeString =
                dictFeignClient.getName(DictEnum.CERTIFICATES_TYPE.getDictCode(),
                        patient.getCertificatesType());
        //联系人证件类型
        String contactsCertificatesTypeString =
                dictFeignClient.getName(DictEnum.CERTIFICATES_TYPE.getDictCode(),
                        patient.getContactsCertificatesType());
        //省
        String provinceString = dictFeignClient.getName(patient.getProvinceCode());
        //市
        String cityString = dictFeignClient.getName(patient.getCityCode());
        //区
        String districtString = dictFeignClient.getName(patient.getDistrictCode());

        patient.getParam().put("certificatesTypeString", certificatesTypeString);
        patient.getParam().put("contactsCertificatesTypeString", contactsCertificatesTypeString);
        patient.getParam().put("provinceString", provinceString);
        patient.getParam().put("cityString", cityString);
        patient.getParam().put("districtString", districtString);
        patient.getParam().put("fullAddress", provinceString + cityString + districtString + patient.getAddress());

        return patient;
    }
}

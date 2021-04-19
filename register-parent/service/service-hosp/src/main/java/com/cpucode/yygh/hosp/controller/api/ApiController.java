package com.cpucode.yygh.hosp.controller.api;

import com.cpucode.yygh.common.exception.YyghException;
import com.cpucode.yygh.common.helper.HttpRequestHelper;
import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.common.result.ResultCodeEnum;
import com.cpucode.yygh.common.utils.MD5;
import com.cpucode.yygh.hosp.service.HospitalService;
import com.cpucode.yygh.hosp.service.HospitalSetService;
import com.cpucode.yygh.model.hosp.Hospital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : cpucode
 * @date : 2021/4/17
 * @time : 23:04
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Api(tags = "医院管理API接口")
@RestController
@RequestMapping("/api/hosp")
public class ApiController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 上传医院接口
     * @param request
     * @return
     */
    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        //获取传递过来医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编码，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4 判断签名是否一致
        if(!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoData = (String)paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);


        hospitalService.save(paramMap);
        return Result.ok();
    }

    /**
     * 查询医院
     * @param request
     * @return
     */
    @ApiOperation(value = "获取医院信息")
    @PostMapping("hospital/show")
    public Result hospital(HttpServletRequest request) {
        //获取传递过来医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //获取医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编码，查询数据库，查询签名
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4 判断签名是否一致
        if(!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);

        return Result.ok(hospital);
    }


}

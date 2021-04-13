package com.cpucode.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpucode.yygh.common.exception.YyghException;
import com.cpucode.yygh.common.result.Result;
import com.cpucode.yygh.common.utils.MD5;
import com.cpucode.yygh.hosp.service.HospitalSetService;
import com.cpucode.yygh.model.hosp.HospitalSet;
import com.cpucode.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author : cpucode
 * @date : 2021/4/13
 * @time : 17:22
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Api(tags = "医院设置管理")
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
     * http://localhost:8201/admin/hosp/hospitalSet/findAll
     *
     * @return
     */
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();

        return Result.ok(list);
    }

    /**
     * 逻辑删除医院设置
     *  http://localhost:8201/swagger-ui.html
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);

        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 条件查询带分页
     * @param current   传递当前页
     * @param limit     每页记录数
     * @param hospitalSetQueryVo
     * @return
     */
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);

        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();

        //医院名称
        String hosname = hospitalSetQueryVo.getHosname();
        //医院编号
        String hoscode = hospitalSetQueryVo.getHoscode();

        if(!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname", hosname);
        }
        if(!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode", hoscode);
        }

        //调用方法实现分页查询
        IPage<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        //返回结果
        return Result.ok(pageHospitalSet);
    }

    /**
{
    "apiUrl": "923992029",
    "contactsName": "cpucode",
    "contactsPhone": "1101",
    "hoscode": "1001",
    "hosname": "湘雅",
    "isDeleted": 0
}
     */
    /**
     * 添加医院设置
     *
     * @param hospitalSet
     * @return
     */
    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态 1 使用 0 不能使用
        hospitalSet.setStatus(1);

        //签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));

        //调用service
        boolean save = hospitalSetService.save(hospitalSet);

        if(save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 根据id获取医院设置
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取医院设置")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);

/*        try {
            //模拟异常
            int a = 1/0;
        }catch (Exception e) {
            throw new YyghException("失败",201);
        }*/


        return Result.ok(hospitalSet);
    }


    /**
{
    "hosname": "中日友好医院",
    "id": 1
}
     */
    /**
     * 修改医院设置
     * @param hospitalSet
     * @return
     */
    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);

        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 批量删除医院设置
     *
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        hospitalSetService.removeByIds(idList);

        return Result.ok();
    }

    /**
     * 医院设置锁定和解锁
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        //根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);

        return Result.ok();
    }

    /**
     *发送签名秘钥
     * @param id
     * @return
     */
    @ApiOperation(value = "发送签名秘钥")
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);

        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();

        //TODO 发送短信

        return Result.ok();
    }

}

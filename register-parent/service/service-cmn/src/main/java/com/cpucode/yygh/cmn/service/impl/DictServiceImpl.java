package com.cpucode.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.yygh.cmn.listener.DictListener;
import com.cpucode.yygh.cmn.mapper.DictMapper;
import com.cpucode.yygh.cmn.service.DictService;
import com.cpucode.yygh.model.cmn.Dict;
import com.cpucode.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/4/15
 * @time : 13:54
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    /**
     * 导入数据字典
     * @param file
     */
    @Override
    @CacheEvict(value = "dict", allEntries=true)
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    DictEeVo.class,
                    new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出数据字典接口
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("cpucode", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        //查询数据库
        List<Dict> dictList = baseMapper.selectList(null);

        //Dict -- DictEeVo
        List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
        for(Dict dict : dictList) {
            DictEeVo dictVo = new DictEeVo();

            // dictEeVo.setId(dict.getId());
            BeanUtils.copyProperties(dict, dictVo, DictEeVo.class);
            dictVoList.add(dictVo);
        }

        try {
            //调用方法进行写操作
            EasyExcel.write(response.getOutputStream(), DictEeVo.class)
                    .sheet("cpucode")
                    .doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据数据id查询子数据列表
     * @param id
     * @return
     */
    @Override
    public List<Dict> findChlidData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);

        List<Dict> dictList = baseMapper.selectList(wrapper);

        //向list集合每个dict对象中设置hasChildren
        for (Dict dict:dictList) {
            Long dictId = dict.getId();
            boolean isChild = this.isChildren(dictId);

            dict.setHasChildren(isChild);
        }
        return dictList;
    }

    /**
     * 判断id下面是否有子节点
     * @param id
     * @return
     */
    private boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();

        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);

        // 0>0    1>0
        return count>0;
    }
}

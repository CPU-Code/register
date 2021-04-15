package com.cpucode.yygh.cmn.controller;

import com.cpucode.yygh.cmn.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : cpucode
 * @date : 2021/4/15
 * @time : 13:51
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {
    @Autowired
    private DictService dictService;

}

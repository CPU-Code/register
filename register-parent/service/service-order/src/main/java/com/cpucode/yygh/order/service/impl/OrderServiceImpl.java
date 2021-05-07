package com.cpucode.yygh.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpucode.yygh.model.order.OrderInfo;
import com.cpucode.yygh.order.mapper.OrderInfoMapper;
import com.cpucode.yygh.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author : cpucode
 * @date : 2021/5/7
 * @time : 14:15
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class OrderServiceImpl extends
        ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderService {

    /**
     * 保存订单
     * @param scheduleId
     * @param patientId
     * @return
     */
    @Override
    public Long saveOrder(String scheduleId, Long patientId) {
        return null;
    }

}

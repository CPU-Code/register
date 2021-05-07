package com.cpucode.common.rabbit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : cpucode
 * @date : 2021/5/7
 * @time : 20:49
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *  发送消息
     * @param exchange 交换机
     * @param routingKey 路由键
     * @param message 消息
     */
    public boolean sendMessage(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }
}

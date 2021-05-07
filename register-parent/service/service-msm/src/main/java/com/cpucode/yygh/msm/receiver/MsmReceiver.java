package com.cpucode.yygh.msm.receiver;

import com.cpucode.common.rabbit.constant.MqConst;
import com.cpucode.yygh.msm.service.MsmService;
import com.cpucode.yygh.vo.msm.MsmVo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : cpucode
 * @date : 2021/5/7
 * @time : 22:12
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Component
public class MsmReceiver {
    @Autowired
    private MsmService smsService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_MSM_ITEM, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_MSM),
            key = {MqConst.ROUTING_MSM_ITEM}))
    public void send(MsmVo msmVo, Message message, Channel channel) {
        smsService.send(msmVo);
    }
}

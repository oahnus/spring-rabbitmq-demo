package com.github.oahnus.springrabbitmqdemo.receiver;

import com.github.oahnus.springrabbitmqdemo.RabbitConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2019/7/16
 * 11:19.
 */
@Component
public class ExchangeReceiver {
    @RabbitListener(queues = RabbitConst.QUEUE)
    public void receiveMessage(String msg) {
        System.out.println("\u001b[34m ExchangeReceiver1 --> " + msg + "\u001b[0m");
    }
    @RabbitListener(queues = RabbitConst.QUEUE_2)
    public void receiveMessage2(String msg) {
        System.out.println("\u001b[32m ExchangeReceiver2 --> " + msg + "\u001b[0m");
    }
    @RabbitListener(queues = RabbitConst.QUEUE_2_MULTI)
    public void receiveMessage2Multi(String msg) {
        System.out.println("\u001b[36m ExchangeReceiver2 Multi --> " + msg + "\u001b[0m");
    }
}

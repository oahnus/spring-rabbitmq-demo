package com.github.oahnus.springrabbitmqdemo.receiver;

import com.github.oahnus.springrabbitmqdemo.RabbitConst;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2019/7/16
 * 10:40.
 */
@Component
public class Receiver {
    @RabbitListener(queues = RabbitConst.HELLO_Q)
    public void receiveMessage(String msg) {
        System.out.println("Receive1 --> " + msg);
    }

    @RabbitListener(queues = RabbitConst.HELLO_Q)
    public void receiveMessage2(String msg) {
        System.out.println("Receiver2 --> " + msg);
    }
}

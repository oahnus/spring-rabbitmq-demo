package com.github.oahnus.springrabbitmqdemo.receiver;

import com.github.oahnus.springrabbitmqdemo.RabbitConst;
import com.sun.media.sound.SoftTuning;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by oahnus on 2019/7/29
 * 15:13.
 */
@Component
public class TimeoutReceiver {
//    @RabbitListener(queues = "TIMEOUT_ROUTING_KEY")
//    public void handleTimeoutMsg(String msg) {
//
//    }

    @RabbitListener(queues = RabbitConst.TIMEOUT_QUEUE)
    public void handleTimeoutHandleMsg(String msg) {
        System.out.println(new Date() + " 接收 " + msg);
    }
}

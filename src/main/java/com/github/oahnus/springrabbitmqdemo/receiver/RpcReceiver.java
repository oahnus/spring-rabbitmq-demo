package com.github.oahnus.springrabbitmqdemo.receiver;

import com.github.oahnus.springrabbitmqdemo.RabbitConst;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

/**
 * Created by oahnus on 2019/7/16
 * 14:46.
 */
@Component
@RabbitListener(queues=RabbitConst.RPC_QUEUE)
public class RpcReceiver {
    @RabbitHandler
    public String sayHello(String name) {
        System.out.println("Receive RPC Request With Args: " + name);
        return "Hello " + name;
    }
}

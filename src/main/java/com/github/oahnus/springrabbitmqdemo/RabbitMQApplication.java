package com.github.oahnus.springrabbitmqdemo;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by oahnus on 2019/7/16
 * 10:36.
 */
@SpringBootApplication
@EnableScheduling
public class RabbitMQApplication {
    @Autowired
    RabbitTemplate template;
    @Autowired
    FanoutExchange fanoutExchange;
    @Autowired
    DirectExchange directExchange;

    public static void main(String... args) {
        SpringApplication.run(RabbitMQApplication.class, args);
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 2000)
    public void schedule() {
        // 1 sendToQueue
//        template.convertAndSend(RabbitConst.HELLO_Q, "Hello Msg");

        // 2 sendToFanoutExchange
//        template.convertAndSend(fanoutExchange.getName(), "", "Fanout Msg " + new Date());

        // 3 SendToDirectExchange
//        template.convertAndSend(RabbitConst.DIRECT_EX, "2", "Direct Msg 2 " + new Date());

        // 4 SendToTopicExchange
        // * 匹配一个单词 # 匹配 0个或多个单词  单词数最多255个
//        template.convertAndSend(RabbitConst.TOPIC_EX, "apple.topic1.apple", "Topic apple.topic1.apple");
//        template.convertAndSend(RabbitConst.TOPIC_EX, "apple.banana.topic1.apple", "Topic apple.banana.topic1.apple");
//        template.convertAndSend(RabbitConst.TOPIC_EX, "topic1.apple", "Topic topic1.apple");
//
//        // these will not work
//        template.convertAndSend(RabbitConst.TOPIC_EX, "dog.cat.topic2.pig", "Topic dog.cat.topic2.pig");
//        template.convertAndSend(RabbitConst.TOPIC_EX, "topic2.pig", "Topic topic2.pig");
//        template.convertAndSend(RabbitConst.TOPIC_EX, "topic2.pig.pig", "Topic topic2.pig.pig");
//        // this is right
//        template.convertAndSend(RabbitConst.TOPIC_EX, "dog.topic2.pig", "Topic dog.topic2.pig");

        // 5 SendToCallBack
//        String name = "luqian";
//        Object receive = template.convertSendAndReceive(RabbitConst.RPC_EX, "rpc", name);
//        String retStr = (String) receive;
//        System.out.println(retStr);

        // 6 SendTimeoutMsg
        String msg = "luqian";
        template.convertAndSend(RabbitConst.TIMEOUT_EXCHANGE, RabbitConst.DO_NOTHING_QUEUE, msg, (message) -> {
            message.getMessageProperties().setExpiration("5000");
            return message;
        });
        System.out.println(new Date() + "发送 => ");
    }
}

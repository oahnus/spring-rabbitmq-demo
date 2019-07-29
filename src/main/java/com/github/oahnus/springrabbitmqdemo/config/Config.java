package com.github.oahnus.springrabbitmqdemo.config;

import com.github.oahnus.springrabbitmqdemo.RabbitConst;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2019/7/16
 * 10:39.
 */
@Configuration
public class Config {

    // exchange type direct topic headers fanout
    // direct exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitConst.DIRECT_EX);
    }

    @Bean
    public Binding directBinding1(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(directExchange).with("1");
    }
    @Bean
    public Binding directBinding2(DirectExchange directExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(directExchange).with("2");
    }
    @Bean
    public Binding directBinding2Multi(DirectExchange directExchange, Queue queue2Multi) {
        return BindingBuilder.bind(queue2Multi).to(directExchange).with("2");
    }

    // fanout exchange
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConst.FANOUT_EX);
    }
    @Bean
    public Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

    // topic exchange
    // * 匹配一个单词 # 匹配 0个或多个单词  单词数最多255个
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConst.TOPIC_EX);
    }
    @Bean
    public Binding topicBinding1(TopicExchange topicExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(topicExchange).with("#.topic1.#");
    }
    @Bean
    public Binding topicBinding2(TopicExchange topicExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(topicExchange).with("*.topic2.*");
    }

    // rpc with mq
    @Bean
    public Queue rpcQueue() {
        return new Queue(RabbitConst.RPC_QUEUE);
    }
    @Bean
    public DirectExchange rpcExchange() {
        return new DirectExchange(RabbitConst.RPC_EX);
    }
    @Bean
    public Binding rpcBinding(DirectExchange rpcExchange, Queue rpcQueue) {
        return BindingBuilder.bind(rpcQueue).to(rpcExchange).with("rpc");
    }

    // queue
    @Bean
    public Queue queue1() {
        return new Queue(RabbitConst.QUEUE);
    }
    @Bean
    public Queue queue2() {
        return new Queue(RabbitConst.QUEUE_2);
    }
    @Bean
    public Queue queue2Multi() {
        return new Queue(RabbitConst.QUEUE_2_MULTI);
    }

    @Bean
    public Queue queue() {
        return new Queue(RabbitConst.HELLO_Q);
    }

    /// 延时队列
    @Bean
    public DirectExchange timeoutExchange(){
        return new DirectExchange(RabbitConst.TIMEOUT_EXCHANGE,true,false,null);
    }

    @Bean
    public Queue doNothingQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", RabbitConst.TIMEOUT_EXCHANGE);
        args.put("x-dead-letter-routing-key", RabbitConst.TIMEOUT_QUEUE);
        return new Queue(RabbitConst.DO_NOTHING_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue timeoutQueue() {
        return new Queue(RabbitConst.TIMEOUT_QUEUE, true, false, false);
    }

    @Bean
    public Binding timeoutQueueBinding() {
        return BindingBuilder
                .bind(doNothingQueue())
                .to(timeoutExchange())
                .with(RabbitConst.DO_NOTHING_QUEUE);
    }

    @Bean
    public Binding timeoutHandleQueueBinding() {
        return BindingBuilder
                .bind(timeoutQueue())
                .to(timeoutExchange())
                .with(RabbitConst.TIMEOUT_QUEUE);
    }
}

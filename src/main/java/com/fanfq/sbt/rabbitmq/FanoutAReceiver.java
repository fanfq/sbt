package com.fanfq.sbt.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${mq.rabbit.queue.a}")
public class FanoutAReceiver {
    private static final Logger logger = LoggerFactory.getLogger(FanoutAReceiver.class);

    @RabbitHandler
    public void process(String message) {
    	logger.info("recv from queue: [a] ,msg:" + message);
    }
}
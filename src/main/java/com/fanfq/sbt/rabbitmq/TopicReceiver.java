package com.fanfq.sbt.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.all")
public class TopicReceiver {
	
    private static final Logger logger = LoggerFactory.getLogger(TopicReceiver.class);

	@RabbitHandler
    public void process(String message) {
        logger.info("Topic ALL Receiver  : " + message);
    }
}

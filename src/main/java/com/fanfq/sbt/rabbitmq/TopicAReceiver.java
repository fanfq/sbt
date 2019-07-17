package com.fanfq.sbt.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.a")
public class TopicAReceiver {
	
    private static final Logger logger = LoggerFactory.getLogger(TopicAReceiver.class);

	@RabbitHandler
    public void process(String message) {
        logger.info("Topic A Receiver  : " + message);
    }
}

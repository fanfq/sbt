package com.fanfq.sbt.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.b")
public class TopicBReceiver {
	
    private static final Logger logger = LoggerFactory.getLogger(TopicBReceiver.class);

	@RabbitHandler
    public void process(String message) {
        logger.info("Topic B Receiver  : " + message);
    }
}

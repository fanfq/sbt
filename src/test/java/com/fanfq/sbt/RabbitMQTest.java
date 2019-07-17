package com.fanfq.sbt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fanfq.sbt.rabbitmq.RabbitController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
	
	@Autowired
    private RabbitController helloSender;

    @Test
    public void hello() throws Exception {
        //helloSender.send();
    }

}

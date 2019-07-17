package com.fanfq.sbt.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.rabbit.exchange.simple}")
    String exchangeName;
    
    @Value("${mq.rabbit.queue.simple}")
    String queueName;
    
    @Value("${mq.rabbit.queue.a}")
    String a;
    
    @Value("${mq.rabbit.queue.b}")
    String b;

    //https://yq.aliyun.com/articles/93063 推荐文章
    
    //localhost:8083/send/fred/mmmqtest
    
    //http://localhost:15672 后台
    @RequestMapping(value = "/send/{name}/{message}", method = RequestMethod.GET)
    public @ResponseBody String send(@PathVariable("name") final String name, @PathVariable("message") final String message) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = simpleDateFormat.format(new Date());
        String sendMessage = "&& 1 -  hello, " + name + ", " + message  + ", " + timeStr;
        
        System.out.println("send to queue "+queueName);
        rabbitTemplate.convertAndSend(queueName,sendMessage);
        
//        senda();
//        sendb();
//        send();
        
        sendTA();
       //sendTB();
        //sendTALL();
        
        return "send message to [" +  name + "] success (" + timeStr + ")";
    }
    
    private void senda() {
    	String context = "&& 2 - hi, i am message 1";
        System.out.println("Sender : " + context +",by exchange:"+exchangeName+",queue:"+a);
        this.rabbitTemplate.convertAndSend(exchangeName, a, context);
    }
    
    public void sendb() {
        String context = "&& 3 - hi, i am messages 2";
        System.out.println("Sender : " + context+",by exchange:"+exchangeName+",queue:"+b);
        this.rabbitTemplate.convertAndSend(exchangeName, b, context);
    }
    
    //发送端的routing_key写任何字符都会被忽略：
    public void send() {
        String context = "&& 4 hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(exchangeName,"", context);
    }
    
    private void sendTA() {
    	String context = "&& ta "+System.currentTimeMillis();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchage", "topic.a", context);
    }
    
    private void sendTB() {
    	String context = "&& tb "+System.currentTimeMillis();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchage", "topic.b", context);
    }
    
    private void sendTALL() {
    	String context = "&& tall "+System.currentTimeMillis();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchage", "topic.all", context);
    }
}

package com.fanfq.sbt.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class RabbitConfig {

    @Value("${mq.rabbit.address}")
    String address;
    @Value("${mq.rabbit.username}")
    String username;
    @Value("${mq.rabbit.password}")
    String password;
    @Value("${mq.rabbit.virtualHost}")
    String mqRabbitVirtualHost;
    @Value("${mq.rabbit.port}")
    int port;
    @Value("${mq.rabbit.host}")
    String host;
    @Value("${mq.rabbit.exchange.simple}")
    String exchangeName;
    @Value("${mq.rabbit.queue.simple}")
    String queuename;
    
    //Fanout
    @Value("${mq.rabbit.queue.a}")
    String queueA;
    @Value("${mq.rabbit.queue.b}")
    String queueB;
    
    //topic
    
    
//    producer或publisher: 消息生产者/发布者，即：产生消息的；
//    Exchange：producer或publisher只会将message发送到Exchange，目前有4种不同的Exchange类型；
//    Queue：消息队列，所有的消费者都是直接从Queue获取Message并消费；
//    Binging：连接Exchange和Queue的纽带，决定Exchange如何路由消息到不同的Queue；
//    routingKey：生产者-->message-->Exchange，需要指定一个key，叫做routingKey;
//    routingKey：Exchange-->Binging-->Queue，Binging有一个Key值，叫routingKey或bingingKey;
//    bingingKey：Exchange-->Binging-->Queue，Binging有一个Key值，bingingKey;

    //创建mq连接
    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(mqRabbitVirtualHost);
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setPublisherConfirms(true);

        //该方法配置多个host，在当前连接host down掉的时候会自动去重连后面的host
        connectionFactory.setAddresses(address);

        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
    
    //队列A
    @Bean
    public Queue aQueue() {
        return new Queue(queueA);
    }
    
    //队列B
    @Bean
    public Queue bQueue() {
        return new Queue(queueB);
    }
    
    /*----------------------------------------
     * Fanout 扇形交换机：将消息分发到所有的绑定队列，无routingkey的概念
     * 这里使用了A、B2个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略
     */
    
    //Fanout交换器
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeName);
    }
    
    //绑定a对列到Fanout交换器
    @Bean
    Binding bindingExchangeA(Queue aQueue,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(aQueue).to(fanoutExchange);
    }
    
    //绑定b对列到Fanout交换器
    @Bean
    Binding bindingExchangeB(Queue bQueue,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(bQueue).to(fanoutExchange);
    }
    
    
    
    /*----------------------------------------
     * HeadersExchange ：通过添加属性key-value匹配
     * 
     */
    
    /*----------------------------------------
     * DirectExchange:按照routingkey分发到指定队列
     * 
     */
    
    /*----------------------------------------
     * TOPIC 主题交换机：多关键字匹配
     * https://yq.aliyun.com/articles/93063
     */
    
  //队列A
    @Bean
    public Queue taQueue() {
        return new Queue("topic.a");
    }
    
    //队列B
    @Bean
    public Queue tbQueue() {
        return new Queue("topic.b");
    }
    
    @Bean
    public Queue tallQueue() {
        return new Queue("topic.all");
    }
    
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchage");
    }
    
    @Bean
    Binding bindingExchangeMessageTA(Queue taQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(taQueue).to(topicExchange).with("topic.a");
    }
    
    @Bean
    Binding bindingExchangeMessageTB(Queue tbQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(tbQueue).to(topicExchange).with("topic.b");
    }

    @Bean
    Binding bindingExchangeMessageTALL(Queue tallQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(tallQueue).to(topicExchange).with("topic.#");
    }

}

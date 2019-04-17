package com.tim.consumer.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ConsumerConfig {

    //    @Bean("rabbitListenerContainerFactory2")
//    public RabbitListenerContainerFactory<?> containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setPrefetchCount(5);
//        factory.setConnectionFactory(connectionFactory);
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }
    @Bean("rabbitListenerContainerFactory2")
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);

        simpleRabbitListenerContainerFactory.setPrefetchCount(20);
//        // 设置消费者线程数
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
//        // 设置最大消费者线程数
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
        //yml中的ack模式会被覆盖   防止出现double ack错误
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        // 设置消费者标签
        simpleRabbitListenerContainerFactory.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String s) {
                return "测试消费者";
            }
        });

        return simpleRabbitListenerContainerFactory;
    }

        @Bean
    public Queue myQueue() {
        Queue queue = new Queue("myQueue1");
        return queue;
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("rtaa");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}

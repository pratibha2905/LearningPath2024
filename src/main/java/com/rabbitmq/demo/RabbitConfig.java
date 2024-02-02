package com.rabbitmq.demo;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory conn=new CachingConnectionFactory();
		return conn;
	}
	
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory factory() {
		SimpleRabbitListenerContainerFactory container= new SimpleRabbitListenerContainerFactory();
		container.setConnectionFactory(connectionFactory());
		return container;
	}//only for consumer part if not their in Rabbit Listner will give exceptions
	
	
	// while creating CachingConnectionFactory( we can pass the host and port )
	//CachingConnectionFactory("localhost",5672);
}

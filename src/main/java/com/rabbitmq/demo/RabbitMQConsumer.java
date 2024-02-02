package com.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
	@RabbitListener(queues="MObile")
	public void getMessage(Person p) {// If not sure about DataTypebyte[] message
		System.out.print("Hello");
		
	}

}

package com.rabbitmq.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit // Without this the consumer will not work 
public class RabbitMVCConsumer {
	// FOr other exchanges
/*	@RabbitListener(queues="MOBILE")
	public void MqListner(Person p) {
		
		System.out.print(p.getName());
		
	} */
	@RabbitListener(queues="MOBILE")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bI=new ByteArrayInputStream(message);
		ObjectInput i=new ObjectInputStream(bI);
		Person p=(Person)i.readObject();
		i.close();
		bI.close();
		System.out.print(p.getName());
		
	}
}

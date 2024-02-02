package com.rabbitmq.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQHeaderConsumer {
	@RabbitListener(queues="Mobile")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException {// If not sure about DataTypebyte[] message
		// We get mesg as byte Array . We have to convert byte array to object of Person class
		//Two ways Third Party Libraries or IO
		ByteArrayInputStream bI=new ByteArrayInputStream(message);
		ObjectInput oI=new ObjectInputStream(bI);
		Person p=(Person) oI.readObject();
		oI.close();
		bI.close();
		System.out.print(p.getName());
		
		
		
	}
}

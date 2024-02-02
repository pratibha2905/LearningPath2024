package com.rabbitmq.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RabbitMVCCOntroller {
	@Autowired
	RabbitTemplate rabbitTemplate;
	// For other Exchanges use same way
/*	@RequestMapping (value="/test/{name}", method=RequestMethod.GET)
	public String TestApi(@PathVariable("name") String name) {
		Person p=new Person(1L, name);
		rabbitTemplate.convertAndSend("Mobile(Queue Name)", p);
		
		return "Success";
	} */
	// FOr Headers Exchange
	@RequestMapping (value="/test/{name}", method=RequestMethod.GET)
	public String TestApi(@PathVariable("name") String name) throws IOException {
		Person p=new Person(1L, name);
		ByteArrayOutputStream b=new ByteArrayOutputStream();
		ObjectOutput o=new ObjectOutputStream(b);
		o.writeObject(p);
		o.flush();
		o.close();
		
		byte[] byteArray=b.toByteArray();
		b.close();
		Message message=MessageBuilder.withBody(byteArray).setHeader("item1","MOBIE").build();
		rabbitTemplate.send("HEaders-Exchange", "", message);
		
		return "Success";
	}
	
	@GetMapping (value="/defaultExchange/{name}")
	public String TestApidefault(@PathVariable("name") String name) {
		Person p=new Person(1L, name);
		rabbitTemplate.convertAndSend("","Mobile(Queue Name)", p);
		
		return "Success";
	}
	
}

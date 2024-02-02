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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	/*@GetMapping("/test/ {name}")
	public String testApi(@PathVariable("name") String name) {  // we will get name in request
		Person p=new Person(1L,name);
		rabbitTemplate.convertAndSend("Mobile", p);// uses simple message converter which 
		//supports string bytes and serialized object
		// if person is not serializable and try to send object it will throw Exception (not Seriazibale Exception)
		
		return "success";
	}*/ // This same procedure for TOPIC,FANOUT and Direct Exchenage 
	
	// For Headers
	@GetMapping("/test/{name}")
	public String testApi(@PathVariable("name") String name) throws IOException {  // we will get name in request
		Person p=new Person(1L,name);
		ByteArrayOutputStream b=new ByteArrayOutputStream();
		ObjectOutput o=new ObjectOutputStream(b);
		o.writeObject(p);
		o.flush();
		o.close();
		
		byte[] byteArray=b.toByteArray();
		b.close();
		
		Message message=MessageBuilder.withBody(byteArray)
				.setHeader("itemq", "Mobile").setHeader("item2", "TV").build();
		rabbitTemplate.send("EXchange_name", "", message );
		
		return name;
	}
	
	
}

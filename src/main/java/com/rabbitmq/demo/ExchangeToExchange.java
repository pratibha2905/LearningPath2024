package com.rabbitmq.demo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ExchangeToExchange {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory connection=new ConnectionFactory();
		Connection conn=connection.newConnection();
		Channel channel =conn.createChannel();
		
		String message="Exchange to Exchange Testing";
		channel.basicPublish("Direct-Exchange", "fanout", null, message.getBytes());
	}

}

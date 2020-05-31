package com.capg.message.operation;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQOperation {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${fanout.exchange}")
	private String exchange;
	
	
	@Value("${routingkey}")
	private String routingkey;	

	public void send(String msg) {
		rabbitTemplate.convertAndSend(exchange, routingkey, msg);
		System.out.println("Send msg = " + msg);

	}
}


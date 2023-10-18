package com.example.Server.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class ServerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(ServerService.class);
    @RabbitListener(queues = "task-rabbitmq-queue")
    public void receiveMessage(String message){
        logger.info("Message Received: "+message);
        message = "Thankyou: I received your message on "+new Date()+" which was: "+message;
        rabbitTemplate.convertAndSend("task-rabbitmq-exchange_server","task-rabbitmq-routing_key_server", message);
    }
}

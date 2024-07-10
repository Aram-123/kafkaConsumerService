package com.example.kafkaConsumerService.service;

import com.example.kafkaConsumerService.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    private final MessageService messageService;

    @Autowired
    public KafkaMessageListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "messages")
    public void listen(Message message) {
        messageService.addMessage(message);
    }
}

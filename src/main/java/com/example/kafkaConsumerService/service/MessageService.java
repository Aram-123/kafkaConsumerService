package com.example.kafkaConsumerService.service;

import com.example.kafkaConsumerService.entity.Message;
import com.example.kafkaConsumerService.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}

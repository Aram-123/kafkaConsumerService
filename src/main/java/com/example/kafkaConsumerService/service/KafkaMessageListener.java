package com.example.kafkaConsumerService.service;

import com.example.kafkaConsumerService.entity.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    private final MessageService messageService;

    @Autowired
    public KafkaMessageListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "messages")
    public void listen(ConsumerRecord<String, Message> record) {
        log.info("Received new message: topic={}, partition={}, offset={}, key={}, value={}", record.topic(), record.partition(), record.offset(), record.key(), record.value());

        try {
            messageService.addMessage(record.value());
            log.info("Successfully processed and saved the message with key={}", record.key());
        } catch (Exception e) {
            log.error("Error processing message with key={}, value={}", record.key(), record.value(), e);
        }
    }
}

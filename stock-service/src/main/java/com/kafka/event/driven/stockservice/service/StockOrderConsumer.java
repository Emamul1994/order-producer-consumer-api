package com.kafka.event.driven.stockservice.service;

import com.kafka.event.driven.basedomainservice.entity.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockOrderConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(StockOrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderMessage(OrderEvent orderEvent) {

        LOGGER.info("Stock Order Service Consuming order {}", orderEvent);

        //save the orderEvent into the database
    }
}

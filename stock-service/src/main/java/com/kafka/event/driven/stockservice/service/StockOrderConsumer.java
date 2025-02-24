package com.kafka.event.driven.stockservice.service;

import com.kafka.event.driven.basedomainservice.entity.OrderEvent;
import com.kafka.event.driven.stockservice.OrderEntity;
import com.kafka.event.driven.stockservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockOrderConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(StockOrderConsumer.class);

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderMessage(OrderEvent orderEvent) {

        LOGGER.info("Stock Order Service Consuming order {}", orderEvent);

        //save the orderEvent into the database
        saveOrder(orderEvent);

    }

    public void saveOrder(OrderEvent orderEvent) {

        //set OrderEntity from OrderEvent
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(orderEvent.getOrder().getOrderId());
        entity.setOrderName(orderEvent.getOrder().getOrderName());
        entity.setQuantity(orderEvent.getOrder().getQuantity());
        entity.setPrice(orderEvent.getOrder().getPrice());
        entity.setMessage(orderEvent.getMessage());
        entity.setStatus(orderEvent.getStatus());

        LOGGER.info("OrderEntity receive for DB persist {}", entity);

        OrderEntity order = orderRepository.save(entity);
    }
}

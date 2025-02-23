package com.kafka.event.driven.emailservice.service;

import com.kafka.event.driven.basedomainservice.entity.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email/api")
public class EmailOrderConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailOrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderMessage(OrderEvent orderEvent) {

        LOGGER.info("Email Service Consuming order {}", orderEvent);

        //send email to the user
    }

}

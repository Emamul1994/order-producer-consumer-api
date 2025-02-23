package com.kafka.event.driven.orderservice.controller;

import com.kafka.event.driven.basedomainservice.entity.Order;
import com.kafka.event.driven.basedomainservice.entity.OrderEvent;
import com.kafka.event.driven.orderservice.service.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders/api")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Order order) {

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order placed in pending state");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);

        orderProducer.sendOrderMessage(orderEvent);

        return "Order placed successfully with order id: " + order.getOrderId();
    }
}

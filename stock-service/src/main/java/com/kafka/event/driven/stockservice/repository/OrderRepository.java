package com.kafka.event.driven.stockservice.repository;

import com.kafka.event.driven.stockservice.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

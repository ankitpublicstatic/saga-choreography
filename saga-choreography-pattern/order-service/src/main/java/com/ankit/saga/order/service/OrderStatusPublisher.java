package com.ankit.saga.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ankit.saga.commons.dto.OrderRequestDto;
import com.ankit.saga.commons.enums.OrderStatus;
import com.ankit.saga.commons.event.OrderEvent;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

  @Autowired
  private Sinks.Many<OrderEvent> orderSinks;

  public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
    OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
    orderSinks.tryEmitNext(orderEvent);
  }
}

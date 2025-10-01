package com.ankit.saga.payment.config;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ankit.saga.commons.enums.OrderStatus;
import com.ankit.saga.commons.event.OrderEvent;
import com.ankit.saga.commons.event.PaymentEvent;
import com.ankit.saga.payment.service.PaymentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class PaymentConsumerConfig {

  @Autowired
  private PaymentService paymentService;

  // This is Payment Consumer that will consume Order event from Order service
  // Flux<OrderEvent> and
  // Publisher from Payment event to Order event Flux<PaymentEvent>
  @Bean
  public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
    return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
  }

  private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
    // get the user id
    // check the balance availability
    // if balance sufficient -> Payment completed and deduct amount from DB
    // if payment not sufficient -> cancel order event and update the amount in DB
    if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
      return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
    } else {
      return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
    }
  }
}

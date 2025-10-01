package com.ankit.saga.order.config;

import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import com.ankit.saga.commons.enums.OrderStatus;
import com.ankit.saga.commons.enums.PaymentStatus;
import com.ankit.saga.order.entity.PurchaseOrder;
import com.ankit.saga.order.repository.OrderRepository;
import com.ankit.saga.order.service.OrderStatusPublisher;
import com.ankit.saga.order.utils.AppUtils;

@Configuration
public class OrderStatusUpdateHandler {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private OrderStatusPublisher publisher;

  @Transactional
  public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {
    repository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
  }

  private void updateOrder(PurchaseOrder purchaseOrder) {
    boolean isPaymentComplete =
        PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
    OrderStatus orderStatus =
        isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
    purchaseOrder.setOrderStatus(orderStatus);
    if (!isPaymentComplete) {
      publisher.publishOrderEvent(AppUtils.convertEntityToDto(purchaseOrder), orderStatus);
    }
  }

}

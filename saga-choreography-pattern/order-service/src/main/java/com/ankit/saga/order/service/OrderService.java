package com.ankit.saga.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ankit.saga.commons.dto.OrderRequestDto;
import com.ankit.saga.commons.enums.OrderStatus;
import com.ankit.saga.order.entity.PurchaseOrder;
import com.ankit.saga.order.repository.OrderRepository;
import com.ankit.saga.order.utils.AppUtils;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderStatusPublisher orderStatusPublisher;

  @Transactional
  public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
    PurchaseOrder order = orderRepository.save(AppUtils.convertDtoToEntity(orderRequestDto));
    orderRequestDto.setOrderId(order.getId());
    // produce kafka event with status ORDER_CREATED
    orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
    return order;
  }

  public List<PurchaseOrder> getAllOrders() {
    return orderRepository.findAll();
  }


}

package com.ankit.saga.order.utils;

import com.ankit.saga.commons.dto.OrderRequestDto;
import com.ankit.saga.commons.enums.OrderStatus;
import com.ankit.saga.order.entity.PurchaseOrder;

public class AppUtils {

  public static PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setProductId(dto.getProductId());
    purchaseOrder.setUserId(dto.getUserId());
    purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
    purchaseOrder.setPrice(dto.getAmount());
    return purchaseOrder;
  }

  public static OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder) {
    OrderRequestDto orderRequestDto = new OrderRequestDto();
    orderRequestDto.setOrderId(purchaseOrder.getId());
    orderRequestDto.setUserId(purchaseOrder.getUserId());
    orderRequestDto.setAmount(purchaseOrder.getPrice());
    orderRequestDto.setProductId(purchaseOrder.getProductId());
    return orderRequestDto;
  }
}

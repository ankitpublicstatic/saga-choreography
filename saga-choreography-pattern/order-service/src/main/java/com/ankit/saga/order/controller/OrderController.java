package com.ankit.saga.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ankit.saga.commons.dto.OrderRequestDto;
import com.ankit.saga.order.entity.PurchaseOrder;
import com.ankit.saga.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService orderService;


  @PostMapping("/create")
  public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
    return orderService.createOrder(orderRequestDto);
  }

  @GetMapping
  public List<PurchaseOrder> getOrders() {
    return orderService.getAllOrders();
  }
}

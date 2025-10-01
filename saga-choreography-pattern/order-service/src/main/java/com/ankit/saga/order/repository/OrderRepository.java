package com.ankit.saga.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ankit.saga.order.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}

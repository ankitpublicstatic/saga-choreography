package com.ankit.saga.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ankit.saga.payment.entity.UserBalance;

public interface UserBalanceRepository extends JpaRepository<UserBalance,Integer> {
}

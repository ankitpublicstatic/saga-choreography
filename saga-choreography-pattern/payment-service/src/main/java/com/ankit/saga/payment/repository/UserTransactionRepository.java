package com.ankit.saga.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ankit.saga.payment.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
}

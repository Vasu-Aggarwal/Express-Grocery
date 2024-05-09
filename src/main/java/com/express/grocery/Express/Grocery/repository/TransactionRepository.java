package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}

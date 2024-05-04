package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}

package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoice_id;
    @Column(nullable = false)
    private Integer invoice_status;
    @Column(nullable = false, unique = true)
    private String invoice_number;
    @Column(unique = true)
    private String invoice_url_path;
    private String shipping_address;
    private String billing_address;
    private Integer billing_contact;

    @CreationTimestamp
    @Column(name = "invoice_date", updatable = false)
    private Timestamp invoice_date;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order_id;

    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InvoiceParticular inp;

}

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
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @Column(name = "invoice_status", nullable = false)
    private Integer invoiceStatus;
    @Column(name = "invoice_number", nullable = false, unique = true)
    private String invoiceNumber;
    @Column(name = "invoice_url_path", unique = true)
    private String invoiceUrlPath;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "billing_address")
    private String billingAddress;
    @Column(name = "billing_contact")
    private Integer billingContact;

    @CreationTimestamp
    @Column(name = "invoice_date", updatable = false)
    private Timestamp invoiceDate;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order_Id;

    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InvoiceParticular inp;

}

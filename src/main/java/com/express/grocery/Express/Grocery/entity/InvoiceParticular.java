package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_particular")
public class InvoiceParticular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ip_id")
    private Integer ipId;

    @Column(name = "basic_amount")
    private Double basicAmount;
    @Column(name = "tax_amount")
    private Double taxAmount;
    @Column(name = "gst_rate")
    private Double gstRate;
    @Column(name = "gst_amount")
    private Double gstAmount;
    @Column(name = "total_amount")
    private Double totalAmount;

    @CreationTimestamp
    @Column(name = "invoice_date", updatable = false)
    private Timestamp invoiceDate;
    @Column(name = "invoice_status")
    private Integer invoiceStatus;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false, unique = true)
    private Invoice invoice;

}

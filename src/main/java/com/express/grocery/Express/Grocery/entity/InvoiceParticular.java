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
    private Integer ip_id;

    private Double basic_amount;
    private Double tax_amount;
    private Double gst_rate;
    private Double gst_amount;
    private Double total_amount;

    @CreationTimestamp
    @Column(name = "invoice_date", updatable = false)
    private Timestamp invoice_date;
    private Integer invoice_status;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false, unique = true)
    private Invoice invoice;

}

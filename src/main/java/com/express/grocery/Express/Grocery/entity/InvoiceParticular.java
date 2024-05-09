package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_particular")
@EntityListeners(AuditingEntityListener.class)
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
    private LocalDateTime invoiceDate;
    @Column(name = "invoice_status")
    private Integer invoiceStatus;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false, unique = true)
    private Invoice invoice;

}

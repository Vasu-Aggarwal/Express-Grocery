package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
@EntityListeners(AuditingEntityListener.class)
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
    private Long billingContact;

    @CreationTimestamp
    @Column(name = "invoice_date", updatable = false)
    private LocalDateTime invoiceDate;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order_Id;

    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false, unique = true)
    private Transaction transaction_id;

    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InvoiceParticular inp;

}

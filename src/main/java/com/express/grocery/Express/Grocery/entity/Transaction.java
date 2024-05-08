package com.express.grocery.Express.Grocery.entity;

import com.express.grocery.Express.Grocery.config.AppConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_Id")
    private Integer transactionId;

    @Column(name = "transaction_amount", nullable = false)
    private Double transactionAmount;

    @Column(name = "transaction_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AppConstants transactionStatus;

    @Column(name = "transaction_mode", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AppConstants transactionMode;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order_id;

    @OneToOne(mappedBy = "transaction_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Invoice invoice;

    @CreationTimestamp
    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @LastModifiedDate
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

}

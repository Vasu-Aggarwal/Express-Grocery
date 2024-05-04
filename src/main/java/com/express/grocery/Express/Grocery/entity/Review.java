package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;
    private Integer rating;
    private String review_comment;

    @CreationTimestamp
    @Column(name = "added_on", updatable = false)
    private Timestamp added_on;

    @LastModifiedDate
    @Column(name = "modified_on")
    private Timestamp modified_on;

    @ManyToOne
    @JoinColumn(name = "reviewed_by")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_reviewed")
    private Product product;
}

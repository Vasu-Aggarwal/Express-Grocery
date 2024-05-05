package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;
    private Integer rating;
    @Column(name = "review_comment")
    private String reviewComment;

    @CreationTimestamp
    @Column(name = "added_on", updatable = false)
    private Timestamp addedOn;

    @LastModifiedDate
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @ManyToOne
    @JoinColumn(name = "reviewed_by", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_reviewed", nullable = false)
    private Product product;
}

package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "reviewed_by")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_reviewed")
    private Product product;

}

package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;

    private Timestamp order_date;
    private Integer order_price;
    private Integer order_status;

    @ManyToOne
    @JoinColumn(name = "ordered_by")
    private User ordered_by;
}

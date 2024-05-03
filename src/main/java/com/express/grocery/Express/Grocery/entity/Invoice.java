package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    private Integer invoice_id;
    private Integer invoice_status;
    private String invoice_number;
    private String invoice_url_path;
    private String shipping_address;
    private String billing_address;
    private Integer billing_contact;

}

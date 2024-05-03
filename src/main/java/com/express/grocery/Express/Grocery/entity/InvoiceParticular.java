package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_particular")
public class InvoiceParticular {

    @Id
    private Integer ip_id;

    private Double basic_amount;
    private Double tax_amount;
    private Double gst_rate;
    private Double gst_amount;
    private Double total_amount;
    private Timestamp invoice_date;
    private Integer invoice_status;

}

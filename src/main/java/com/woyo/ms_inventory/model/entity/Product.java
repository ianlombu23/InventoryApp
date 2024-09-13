package com.woyo.ms_inventory.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock")
    private int stock;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "product_image")
    private String productImage;
}

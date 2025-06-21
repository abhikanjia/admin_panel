package com.learn.adminpanel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_master",
        uniqueConstraints = @UniqueConstraint(columnNames = "ProductName"))
@NoArgsConstructor
@Getter
@Setter
public class ProductMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price")
    private Double productPrice;

    @Lob
    @Column(name = "product_img")
    private byte[] productImg;

    @Column(name = "product_type", nullable = false, length = 50)
    private String productType;

    @Column(name = "product_stock", nullable = false)
    private int productStock;

    @Column(name = "product_label", length = 50)
    private String productLabel;

    public ProductMaster(String productName, Double productPrice, byte[] productImg, String productType, int productStock, String productLabel) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.productType = productType;
        this.productStock = productStock;
        this.productLabel = productLabel;
    }

}
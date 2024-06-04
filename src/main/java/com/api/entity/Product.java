package com.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Product {



    private String productName;
    private Double productPrice;

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public long getUPC() {
        return UPC;
    }

    public void setUPC(long UPC) {
        this.UPC = UPC;
    }

    public double getProductWeight(double numericCellValue) {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public int getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(int productStocks) {
        this.productStocks = productStocks;
    }

    @Id
    private String SKU;
    private long UPC;
    private double productWeight;
    private String productManufacturer;
    private int productStocks;

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }



    private LocalDateTime addedDate;

//
//    public Product(String productName, Double productPrice, LocalDateTime addedDate) {
//        this.productName = productName;
//        this.productPrice = productPrice;
//
//    }
    public Product(String productName, Double productPrice, String SKU, long UPC, double productWeight, String productManufacturer, int productStocks, LocalDateTime addedDate) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.SKU = SKU;
        this.UPC = UPC;
        this.productWeight = productWeight;
        this.productManufacturer = productManufacturer;
        this.productStocks = productStocks;
        this.addedDate = addedDate;
    }

    public Product() {
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}

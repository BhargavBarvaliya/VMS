package com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Column(length = 500)
    private String productName;
    private Double productPrice;

    public String getSKU() {
        return SKU;
    }
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
//    public double getProductWeight(double numericCellValue) {
//        return productWeight;
//    }
//
//    public void setProductWeight(double productWeight) {
//        this.productWeight = productWeight;
//    }
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
    public String getUPC() {
        return UPC;
    }
    public void setUPC(String UPC) {
        this.UPC = UPC;
    }
    private String UPC;
   // private double productWeight;
    private String productManufacturer;
    private int productStocks;
    private String gender;
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
//
//    public Product(String productName, Double productPrice, LocalDateTime addedDate) {
//        this.productName = productName;
//        this.productPrice = productPrice;
//
//    }
    public Product(String productName, Double productPrice, String SKU, String UPC/*, double productWeight*/, String productManufacturer, int productStocks, String gender) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.SKU = SKU;
        this.UPC = UPC;
       // this.productWeight = productWeight;
        this.productManufacturer = productManufacturer;
        this.productStocks = productStocks;
        this.gender = gender;
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

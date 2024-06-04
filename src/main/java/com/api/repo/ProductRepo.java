package com.api.repo;

import com.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query("SELECT p.UPC, COUNT(p.UPC) FROM Product p GROUP BY p.UPC HAVING COUNT(p.UPC) > 1")
    List<Object[]> findDuplicateUPCs();

    @Query("SELECT p.UPC, p.productManufacturer, MAX(p.productPrice) FROM Product p GROUP BY p.UPC, p.productManufacturer")
    List<Object[]> findHighestPriceByUPC();

    @Query("SELECT p.UPC, p.productManufacturer, MIN(p.productPrice) FROM Product p GROUP BY p.UPC, p.productManufacturer")
    List<Object[]> findLowestPriceByUPC();

    @Query("SELECT p.productManufacturer, p.UPC FROM Product p WHERE p.productStocks = 0 or p.productStocks = Null")
    List<Object[]> findOutOfStockUPCs();
}

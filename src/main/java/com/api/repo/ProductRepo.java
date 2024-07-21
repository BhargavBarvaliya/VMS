package com.api.repo;

import com.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
//    @Query(value = "SELECT * " +
//            "FROM Product p as a " +
//            "JOIN ( " +
//            "    SELECT p.UPC, COUNT(*) as ProductCount " +
//            "    FROM Product p" +
//            "    GROUP BY p.UPC " +
//            "    HAVING COUNT(*) > 1 " +
//            ") b ON a.UPC = b.UPC " +
//            "ORDER BY b.vendorCount DESC",
//            nativeQuery = true)
//    List<Object[]> findDuplicateUPCs();
//@Query(value = "SELECT a.*, " +
//        "FROM product a " +
//        "JOIN ( " +
//        "    SELECT UPC, COUNT(*) as ProductCount " +
//        "    FROM product " +
//        "    GROUP BY UPC " +
//        "    HAVING COUNT(*) > 1 " +
//        ") b ON a.UPC = b.UPC " +
//        "ORDER BY b.ProductCount DESC",
//        nativeQuery = true)

//    @Query(value = "SELECT * " +
//            "FROM Product p as a " +
//            "JOIN ( " +
//            "    SELECT p.UPC, COUNT(*) as ProductCount " +
//            "    FROM Product p" +
//            "    GROUP BY p.UPC " +
//            "    HAVING COUNT(*) > 1 " +
//            ") b ON a.UPC = b.UPC " +
//            "ORDER BY b.vendorCount DESC",
//            nativeQuery = true)
//@Query("SELECT p.SKU, p.UPC,p.gender, p.productManufacturer, p.productName, p.productPrice, p.productStocks " +
//        "FROM Product p " +
//        "WHERE p.UPC IN (SELECT p2.UPC FROM Product p2 GROUP BY p2.UPC HAVING COUNT(p2.UPC) > 1)")
@Query(value = "SELECT " +
        "   ROW_NUMBER() OVER () AS serial_number, " +
        "   upc, " +
        "   COUNT(*) AS Duplicate_Count, " +
        "   MAX(CASE WHEN rn = 1 THEN sku END) AS SKU_1, " +
        "   MAX(CASE WHEN rn = 1 THEN product_price END) AS price_1, " +
        "   MAX(CASE WHEN rn = 1 THEN product_stocks END) AS stock_1, " +
        "   MAX(CASE WHEN rn = 2 THEN sku END) AS SKU_2, " +
        "   MAX(CASE WHEN rn = 2 THEN product_price END) AS price_2, " +
        "   MAX(CASE WHEN rn = 2 THEN product_stocks END) AS stock_2, " +
        "   MAX(CASE WHEN rn = 3 THEN sku END) AS SKU_3, " +
        "   MAX(CASE WHEN rn = 3 THEN product_price END) AS price_3, " +
        "   MAX(CASE WHEN rn = 3 THEN product_stocks END) AS stock_3, " +
        "   MAX(CASE WHEN rn = 4 THEN sku END) AS SKU_4, " +
        "   MAX(CASE WHEN rn = 4 THEN product_price END) AS price_4, " +
        "   MAX(CASE WHEN rn = 4 THEN product_stocks END) AS stock_4, " +
        "   MAX(CASE WHEN rn = 5 THEN sku END) AS SKU_5, " +
        "   MAX(CASE WHEN rn = 5 THEN product_price END) AS price_5, " +
        "   MAX(CASE WHEN rn = 5 THEN product_stocks END) AS stock_5 " +
        "FROM ( " +
        "   SELECT upc, sku, product_price, product_stocks, " +
        "          ROW_NUMBER() OVER (PARTITION BY upc ORDER BY sku) AS rn " +
        "   FROM product p " +
        ") AS numbered " +
        "WHERE upc IN ( " +
        "   SELECT upc " +
        "   FROM product p " +
        "   GROUP BY upc " +
        "   HAVING COUNT(*) > 1 " +
        ") " +
        "GROUP BY upc " +
        "ORDER BY serial_number", nativeQuery = true)
List<Object[]> findDuplicateUPCs();

//    @Query("SELECT p.UPC, p.productManufacturer, p.productPrice, COUNT(p.UPC) as vendorCount " +
//            "FROM Product p " +
//            "GROUP BY p.UPC, p.productManufacturer, p.productPrice " +
//            "HAVING COUNT(p.UPC) > 1")
//    List<Object[]> findDuplicateUPCs();
    //@Query("SELECT p.SKU,p.UPC,p.gender, p.productManufacturer,p.productName, p.productPrice,p.productStocks FROM Product p WHERE p.productPrice = (SELECT MAX(p.productPrice) FROM p)")
@Query(value = "SELECT row_number() OVER (ORDER BY product_stocks DESC) as serial_no, " +
        "sku, upc, gender, product_name,product_manufacturer, product_price, product_stocks " +
        "FROM ( " +
        "   SELECT *, PERCENT_RANK() OVER (ORDER BY product_stocks DESC) as percentile_rank " +
        "   FROM product p " +
        ") AS p1 " +
        "WHERE  ( percentile_rank <= 0.1 )",
        nativeQuery = true)
    List<Object[]> findHighestPriceByUPC();

//    @Query(value = "SELECT @rownum := @rownum + 1 AS sr_no, p.SKU, p.UPC,p.gender, p.productManufacturer, p.productName, p.productPrice, p.productStocks, p.productWeight " +
//            "FROM Product p, (SELECT @rownum := 0) r " +
//            "WHERE p.productPrice = (SELECT MIN(p2.productPrice) FROM Product p2 WHERE p2.UPC = p.UPC)",
//            nativeQuery = true)
//@Query("SELECT p.SKU,p.UPC,p.gender, p.productManufacturer,p.productName, p.productPrice,p.productStocks FROM Product p WHERE p.productPrice = (SELECT MIN(p.productPrice) FROM p)")
@Query(value = "SELECT " +
        "   row_number() OVER (ORDER BY product_stocks ASC) as serial_no, " +
        "   SKU, UPC, gender , Product_Name,product_manufacturer, Product_Price, product_stocks " +
        "FROM ( " +
        "   SELECT *, PERCENT_RANK() OVER (ORDER BY product_stocks ASC) as percentile_rank " +
        "   FROM Product p " +
        ") AS p1 " +
        "WHERE percentile_rank <= 0.1",
        nativeQuery = true)
    List<Object[]> findLowestPriceByUPC();
//    @Query("SELECT p.UPC, p.productManufacturer, MAX(p.productPrice) " +
//            "FROM Product p ")
//    List<Object[]> findHighestPriceByUPC();

    //    @Query(value = "SELECT * FROM ( " +
//            "   SELECT *, PERCENT_RANK() OVER (ORDER BY product_price DESC) AS percentile_rank " +
//            "   FROM product " +
//            ") AS p1 " +
//            "WHERE percentile_rank <= 0.1",
//            nativeQuery = true)
//    @Query(value = "SELECT * FROM ( " +
//            "   SELECT " +
//            "       ROW_NUMBER() OVER (ORDER BY product_price DESC) AS serial_number, " +
//            "       product.*, " +
//            "       PERCENT_RANK() OVER (ORDER BY product_price DESC) AS percentile_rank " +
//            "   FROM product " +
//            ") AS p1 " +
//            "WHERE p1.percentile_rank <= 0.1",
//            nativeQuery = true)
    @Query(value = "SELECT " +
            "ROW_NUMBER() OVER (ORDER BY product_price DESC) AS serial_number, " +
            "sku, upc, gender,product_name,product_manufacturer, product_price, product_stocks " +
            "FROM ( " +
            "   SELECT *, PERCENT_RANK() OVER (ORDER BY product_price DESC) AS percentile_rank " +
            "   FROM product " +
            ") AS p1 " +
            "WHERE percentile_rank <= 0.1",
            nativeQuery = true)
    List<Object[]> findHighest10PercentileProductsByPrice();

//    @Query("SELECT p.UPC, p.productManufacturer, MIN(p.productPrice) " +
//            "FROM Product p ")
//    List<Object[]> findLowestPriceByUPC();
@Query(value = "SELECT " +
        "ROW_NUMBER() OVER (ORDER BY product_price ASC) AS serial_number, " +
        "sku, upc, gender, product_name,product_manufacturer, product_price, product_stocks " +
        "FROM ( " +
        "   SELECT *, PERCENT_RANK() OVER (ORDER BY product_price ASC) AS percentile_rank " +
        "   FROM product " +
        ") AS p1 " +
        "WHERE percentile_rank <= 0.1",
        nativeQuery = true)
    List<Object[]> findBottom10PercentileProductsByPrice();

    @Query("SELECT p.SKU, p.UPC,p.gender, p.productManufacturer,p.productName, p.productPrice,p.productStocks FROM Product p WHERE p.productStocks = 0 or p.productStocks = Null")
    List<Object[]> findOutOfStockUPCs();
    @Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY a.upc) AS serial_number, " +
            "a.sku, a.upc,a.gender, a.product_name, a.product_manufacturer, a.product_price, a.product_stocks " +
            "FROM product a " +
            "JOIN ( " +
            "    SELECT upc, COUNT(*) as Duplicate_Count " +
            "    FROM product " +
            "    GROUP BY upc " +
            "    HAVING COUNT(*) <= 1 " +
            "    ORDER BY COUNT(*) " +
            ") b ON a.upc = b.upc",
            nativeQuery = true)
    List<Object[]> findNotDuplicateUPCs();
}

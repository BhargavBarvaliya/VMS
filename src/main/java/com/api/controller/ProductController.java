package com.api.controller;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/duplicates/export")
    public ResponseEntity<InputStreamResource> exportDuplicateUPCs() {
        ByteArrayInputStream in = productService.getDuplicateUPCsCSV();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Duplicate UPCs.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/product/notduplicates/export")
    public ResponseEntity<InputStreamResource> exportNotDuplicateUPCs() {
        ByteArrayInputStream in = productService.getNotDuplicateUPCsCSV();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Non Duplicate UPCs.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/product/lowest-stock/export")
    public ResponseEntity<InputStreamResource> exportLowestPrices() {
        ByteArrayInputStream in = productService.getLowestPricesCSV();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Lowest 10% Stock.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/product/highest-price10/export")
    public ResponseEntity<InputStreamResource> exportHighest10PercentileProductsByPrice() {
        ByteArrayInputStream in = productService.getHighest10PercentileProductsByPrice();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Highest 10% Prices.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
    @GetMapping("/product/lowest-price10/export")
    public ResponseEntity<InputStreamResource> exportBottom10PercentileProductsByPrice() {
        ByteArrayInputStream in = productService.getBottom10PercentileProductsByPrice();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Lowest 10% Prices.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
    @GetMapping("/product/highest-stock/export")
    public ResponseEntity<InputStreamResource> exportHighestPrices() {
        ByteArrayInputStream in = productService.getHighestPricesCSV();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=highest 10% Stock.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/product/out-of-stock/export")
    public ResponseEntity<InputStreamResource> exportOutOfStockUPCs() {
        ByteArrayInputStream in = productService.getOutOfStockUPCsCSV();
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Out of Stock UPCs.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true
            var fileName = file.getOriginalFilename();
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", fileName+" File is uploaded and data is saved to the DB"));
        }
       // else{this.productService.save(file);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file");
    //}
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/product/duplicates")
    public List<Object[]> getDuplicateUPCs() {
        return this.productService.findDuplicateUPCs();
    }
    @GetMapping("/product/notduplicates")
    public List<Object[]> getNotDuplicateUPCs() {
        return this.productService.findNotDuplicateUPCs();
    }

    @GetMapping("/product/lowest-price")
    public List<Object[]> getLowestPriceByUPC() {
        return this.productService.findLowestPriceByUPC();
    }

    @GetMapping("/product/highest-price")
    public List<Object[]> getHighestPriceByUPC() {
        return this.productService.findHighestPriceByUPC();
    }

    @GetMapping("/product/lowest-price10")
    public List<Object[]> getHighest10PercentileProductsByPrice() {
        return this.productService.findHighest10PercentileProductsByPrice();
    }
    @GetMapping("/product/highest-price10")
    public List<Object[]> getBottom10PercentileProductsByPrice() {
        return this.productService.findBottom10PercentileProductsByPrice();
    }
    @GetMapping("/product/out-of-stock")
    public List<Object[]> getOutOfStockUPCs() {
        return this.productService.findOutOfStockUPCs();
    }
}

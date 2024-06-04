package com.api.controller;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
        }
       // else{this.productService.save(file);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    //}
    }


    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/products/duplicates")
    public List<Object[]> getDuplicateUPCs() {
        return this.productService.findDuplicateUPCs();
    }

    @GetMapping("/products/lowest-price")
    public List<Object[]> getLowestPriceByUPC() {
        return this.productService.findLowestPriceByUPC();
    }

    @GetMapping("/products/highest-price")
    public List<Object[]> getHighestPriceByUPC() {
        return this.productService.findHighestPriceByUPC();
    }

    @GetMapping("/products/out-of-stock")
    public List<Object[]> getOutOfStockUPCs() {
        return this.productService.findOutOfStockUPCs();
    }
    @GetMapping("/products/newly-added")
    public List<Product> getNewlyAddedUPCs(@RequestParam("since") LocalDateTime since) {
        return this.productService.findNewlyAddedUPCs(since);
    }
}

package com.api.service;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.repo.ProductRepo;
import com.api.repo.ProductHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Object[]> findDuplicateUPCs() {
        return productRepo.findDuplicateUPCs();
    }
    public List<Object[]> findHighestPriceByUPC() {
        return productRepo.findHighestPriceByUPC();
    }

    public List<Object[]> findLowestPriceByUPC() {
        return productRepo.findLowestPriceByUPC();
    }

    public List<Object[]> findOutOfStockUPCs() {
        return productRepo.findOutOfStockUPCs();
    }

    public List<Product> findNewlyAddedUPCs(LocalDateTime since) {
        return ProductHistoryRepo.findNewlyAddedUPCs(since);
    }
    public void saveProduct(Product product) {
        this.productRepo.save(product);
    }

    public void save(MultipartFile file) {

        try {
            List<Product> products;
            if (file.getContentType().equals("text/csv")) {
                products = Helper.convertCsvToListOfProduct(file.getInputStream());
            } else {
                products = Helper.convertExcelToListOfProduct(file.getInputStream());
            }
            //List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
            this.productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();

    }
}

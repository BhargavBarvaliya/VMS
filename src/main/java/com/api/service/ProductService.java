package com.api.service;

import com.api.entity.Product;
import com.api.helper.Helper;
import com.api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.helper.CSVHelper;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;



    public ByteArrayInputStream getDuplicateUPCsCSV() {
        List<Object[]> data = productRepo.findDuplicateUPCs();
        return CSVHelper.duplicateUPCsToCSV(data);
    }
    public ByteArrayInputStream getNotDuplicateUPCsCSV() {
        List<Object[]> data = productRepo.findNotDuplicateUPCs();
        return CSVHelper.notDuplicateUPCsToCSV(data);
    }

    public ByteArrayInputStream getBottom10PercentileProductsByPrice() {
        List<Object[]> data = productRepo.findBottom10PercentileProductsByPrice();
        return CSVHelper.Bottom10PercentileProductsByPrice(data);
    }

    public ByteArrayInputStream getLowestPricesCSV() {
        List<Object[]> data = productRepo.findLowestPriceByUPC();
        return CSVHelper.lowestPricesToCSV(data);
    }

    public ByteArrayInputStream getHighestPricesCSV() {
        List<Object[]> data = productRepo. findHighestPriceByUPC();
        return CSVHelper.highestPricesToCSV(data);
    }

    public ByteArrayInputStream getHighest10PercentileProductsByPrice() {
        List<Object[]> data = productRepo. findHighest10PercentileProductsByPrice();
        return CSVHelper.Highest10PercentileProductsByPrice(data);
    }

    public ByteArrayInputStream getOutOfStockUPCsCSV() {
        List<Object[]> data = productRepo.findOutOfStockUPCs();
        return CSVHelper.outOfStockUPCsToCSV(data);
    }
//public List<Object[]> getOutOfStockUPCsCSV() {
//    List<Object[]> products = productRepo.findOutOfStockUPCs();
//    List<Object[]> result = new ArrayList<>();
//    int serial = 1;
//
//    for (Object[] product : products) {
//        result.add(new Object[]{serial++, product[0], product[1]});
//    }
//
//    return result;
//}

    public List<Object[]> findDuplicateUPCs() {
        return productRepo.findDuplicateUPCs();
    }
    public List<Object[]> findNotDuplicateUPCs() {
        return productRepo.findNotDuplicateUPCs();
    }
    public List<Object[]> findHighestPriceByUPC() {
        return productRepo.findHighestPriceByUPC();
    }
    public List<Object[]> findLowestPriceByUPC() {
        return productRepo.findLowestPriceByUPC();
    }
    public List<Object[]> findHighest10PercentileProductsByPrice() {
        return productRepo.findHighest10PercentileProductsByPrice();
    }
    public List<Object[]> findBottom10PercentileProductsByPrice() {
        return productRepo.findBottom10PercentileProductsByPrice();
    }
    public List<Object[]> findOutOfStockUPCs() {
        return productRepo.findOutOfStockUPCs();
    }


    public void saveProduct(Product product) {
        this.productRepo.save(product);
    }

    @Transactional
    public void save(MultipartFile file) {

        try {
            List<Product> products;
            if (file.getContentType().equals("text/csv")) {
                products = Helper.convertCsvToListOfProduct(file.getInputStream());
            } else {
                products = Helper.convertExcelToListOfProduct(file.getInputStream());
            }
//            firstProducts= products/2 fisrt
//                    secondproducts = products/2 last
            //List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());
//            products[0:50000] , products[50000:10000]
            int batchSize = 1000;
            for (int i = 0; i < products.size(); i += batchSize) {
                int end = Math.min(i + batchSize, products.size());
                List<Product> batch = products.subList(i, end);
                productRepo.saveAll(batch);
            this.productRepo.saveAll(products);
//            this.productRepo.saveAll(products[]);

        }}catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();

    }
}

package com.api.repo;

import com.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductHistoryRepo extends JpaRepository<Product, String> {
    @Query("SELECT ph FROM Product ph WHERE ph.addedDate > :since")
    static List<Product> findNewlyAddedUPCs(LocalDateTime since) {
        return null;
    }


}

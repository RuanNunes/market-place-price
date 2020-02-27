package com.market.repository;

import com.market.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    static String sql = "";
//
//    @Query(value = sql, nativeQuery = true)
//    Page<Product> findProducts(String ruleName, String productName, Pageable page);

    @Transactional(readOnly = true)
    Page<Product> findByNameContaining(String name, Pageable page);
//
//    @Transactional(readOnly = true)
//    Page<Product> findDistinctByNameContainingAndCostumersIn(String name, List<Customer> costumers, Pageable page);

}

package com.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.model.Customer;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from costumer c" +
            " where c.name like %?1%", nativeQuery = true)
    Page<Customer> findCostumers(String name, Pageable page);

    @Transactional(readOnly = true)
    Page<Customer> findByNameContaining(String name, Pageable page);
}

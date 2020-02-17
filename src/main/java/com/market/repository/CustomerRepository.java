package com.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.market.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from customer c" +
            " where c.name like %?1%", nativeQuery = true)
    Page<Customer> findCustomers(String name, Pageable page);

    @Transactional(readOnly = true)
    Page<Customer> findByNameContaining(String name, Pageable page);
}

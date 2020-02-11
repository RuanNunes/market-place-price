package com.market.repository;

import com.market.model.RuleMarketPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.model.Costumer;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    @Query(value = "select * from costumer c" +
            " where c.name like %?1%", nativeQuery = true)
    Page<Costumer> findCostumers(String name, Pageable page);

    @Transactional(readOnly = true)
    Page<Costumer> findByNameContaining(String name, Pageable page);
}

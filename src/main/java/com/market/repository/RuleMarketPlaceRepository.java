package com.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.model.RuleMarketPlace;
@Repository
public interface RuleMarketPlaceRepository extends JpaRepository<RuleMarketPlace, Long> {

    @Query(value = "select * from rule_market_place r" +
                    " where r.name like %?1%", nativeQuery = true)
    Page<RuleMarketPlace> findRules(String name, Pageable page);
}

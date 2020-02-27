package com.market.repository;

import com.market.model.RuleMarketPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface RuleMarketPlaceRepository extends JpaRepository<RuleMarketPlace, Long> {
	static String sql = "select * from rule_market_place rmp "
	+ " where rmp.name like %:name%";
	
    @Query(value = sql, nativeQuery = true)
    Page<RuleMarketPlace> findRules(String name, Pageable page);
    
    @Transactional(readOnly = true)
	Page<RuleMarketPlace> findDistinctByNameContaining(String name,  Pageable page);
    
//    @Transactional(readOnly = true)
//	Page<RuleMarketPlace> findDistinctByNameContainingAndProductsIn(String name, List<Product> products, Pageable page);
}

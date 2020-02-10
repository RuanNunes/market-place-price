package com.market.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.market.model.Product;
import com.market.model.RuleMarketPlace;
@Repository
public interface RuleMarketPlaceRepository extends JpaRepository<RuleMarketPlace, Long> {
	static String sql = "select * from rule_market_place rmp " 
	+ " left join product_rule pro on" 
	+ " pro.rule_id = rmp.id "
	+ " left join product p on" 
	+ " p.id  = pro.product_id" 

	+ " where rmp.name like %:name%";
	
    @Query(value = sql, nativeQuery = true)
    Page<RuleMarketPlace> findRules(String name, Pageable page);
    
    @Transactional(readOnly = true)
	Page<RuleMarketPlace> findDistinctByNameContaining(String name,  Pageable page);
    
    @Transactional(readOnly = true)
	Page<RuleMarketPlace> findDistinctByNameContainingAndProductsIn(String name, List<Product> products, Pageable page);
}

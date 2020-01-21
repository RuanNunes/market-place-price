package com.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.model.RuleMarketPlace;
@Repository
public interface RuleMarketPlaceRepository extends JpaRepository<RuleMarketPlace, Long> {

}

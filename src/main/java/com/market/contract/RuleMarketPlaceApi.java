package com.market.contract;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.market.contract.dto.RuleMarketPlaceDTO;

public interface RuleMarketPlaceApi {
	@PostMapping("/api/v1/rule")
	public ResponseEntity<Void> saveRuleMarketPlace(@Valid @RequestBody final RuleMarketPlaceDTO dto);
}

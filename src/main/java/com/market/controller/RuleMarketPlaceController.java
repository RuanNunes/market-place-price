package com.market.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.market.contract.RuleMarketPlaceApi;
import com.market.contract.dto.RuleMarketPlaceDTO;
import com.market.service.RuleMarketPlaceService;

@Validated
@RestController
public class RuleMarketPlaceController implements RuleMarketPlaceApi{
	
	@Autowired
	private RuleMarketPlaceService service;
	
	@Override
	public ResponseEntity<Void> saveRuleMarketPlace(@Valid RuleMarketPlaceDTO dto) {
		final var obj = service.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}

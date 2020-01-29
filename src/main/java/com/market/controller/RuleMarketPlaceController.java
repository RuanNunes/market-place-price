package com.market.controller;

import java.net.URI;
import java.util.List;

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
	public ResponseEntity<Void> save(@Valid RuleMarketPlaceDTO dto) {
		final var obj = service.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<RuleMarketPlaceDTO> find(String id) {
		return ResponseEntity.ok().body(service.find(Long.parseLong(id)));
	}
	
	@Override
	public ResponseEntity<List<RuleMarketPlaceDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	public ResponseEntity<?> findPaginate(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

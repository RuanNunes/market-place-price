package com.market.controller;

import com.market.contract.RuleMarketPlaceApi;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.RuleMarketPlaceDTO;
import com.market.contract.dto.filters.RuleMarketPlaceFiltersDTO;
import com.market.service.RuleMarketPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
	public ResponseEntity<RuleMarketPlaceDTO> find(Long id) {
		return ResponseEntity.ok().body(service.find(id));
	}

	@Override
	public ResponseEntity<List<RuleMarketPlaceDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	public ResponseEntity<RuleMarketPlaceDTO> update(@Valid RuleMarketPlaceDTO objDTO, Long id) {
		return ResponseEntity.ok().body(service.update(objDTO,id));
	}

	@Override
	public ResponseEntity<PaginatedResourceDTO<RuleMarketPlaceDTO>> findPaginate(@Valid RuleMarketPlaceFiltersDTO filters) {
		return ResponseEntity.ok().body(service.findPaginate(filters));
	}

}

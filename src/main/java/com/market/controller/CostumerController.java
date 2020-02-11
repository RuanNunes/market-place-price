package com.market.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.filters.CustomerFiltersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.market.contract.CostumerApi;
import com.market.contract.dto.CostumerDTO;
import com.market.service.CostumerService;

@Valid
@RestController
public class CostumerController implements CostumerApi {
	@Autowired
	private CostumerService service;

	@Override
	public ResponseEntity<Void> save(@Valid @RequestBody CostumerDTO custumer) {
		final var obj = service.save(custumer);
		//retorna url com novo registro inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<CostumerDTO> find(Long id) {
		return ResponseEntity.ok().body(service.find(id));
	}

	@Override
	public ResponseEntity<List<CostumerDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	public ResponseEntity<CostumerDTO> update(@Valid @RequestBody CostumerDTO dto, Long id) {
		return ResponseEntity.ok().body(service.update(dto, id));
	}

	@Override
	public ResponseEntity<PaginatedResourceDTO<CostumerDTO>> findPaginate(@Valid CustomerFiltersDTO filters) {
		return ResponseEntity.ok().body(service.findPaginate(filters));
	}
}


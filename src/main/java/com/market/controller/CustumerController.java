package com.market.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.market.contract.CustumerApi;
import com.market.contract.dto.CostumerDTO;
import com.market.service.CustumerService;

@Valid
@RestController
public class CustumerController implements CustumerApi{
	@Autowired
	private CustumerService service;
	
	@Override
	public ResponseEntity<Void> save(@Valid @RequestBody CostumerDTO custumer){
		final var obj = service.save(custumer);
		//retorna url com novo registro inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<CostumerDTO> find(String id) {
		return ResponseEntity.ok().body(service.find(Long.parseLong(id)));
	}

	@Override
	public ResponseEntity<List<CostumerDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@Override
	public ResponseEntity<?> findPaginate(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.market.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.market.model.Custumer;
import com.market.service.CustumerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/market-price/clientes")
public class CustumerController {
	@Autowired
	private CustumerService service;
	
	@ApiOperation(value="Busca por todos clientes") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "nenhum cliente encontrado") })
	@GetMapping
	public ResponseEntity<?> findall() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@ApiOperation(value="Busca por cliente-id") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "cliente não encontrado") }) 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.find(id));
	}
	
	@ApiOperation(value="Inclusão de cliente")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Custumer custumer){
		final var obj = service.save(custumer);
		//retorna url com novo registro inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

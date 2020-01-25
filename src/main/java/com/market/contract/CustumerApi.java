package com.market.contract;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.market.contract.dto.CustumerDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api/v1/custumers/")
public interface CustumerApi extends GenericApi<CustumerDTO> {
	
	@Override
	@ApiOperation(value="Inclusão de custumer")
	@PostMapping()
	public ResponseEntity<Void> save(@Valid @RequestBody CustumerDTO dto);
	
	@Override
	@ApiOperation(value="Busca por custumer-id") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "custumer não encontrado") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CustumerDTO> find(@PathVariable String id);
	
	@Override
	@ApiOperation(value="Busca por todos custumer") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "nenhum custumer encontrado") })
	@GetMapping
	public ResponseEntity<List<CustumerDTO>> findAll();
}

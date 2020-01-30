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

import com.market.contract.dto.RuleMarketPlaceDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api/v1/rules/")
public interface RuleMarketPlaceApi extends GenericApi<RuleMarketPlaceDTO> {
	
	@Override
	@ApiOperation(value="Inclusão de Regras Market Place")
	@PostMapping()
	public ResponseEntity<Void> save(@Valid @RequestBody RuleMarketPlaceDTO dto);
	
	@Override
	@ApiOperation(value="Busca por Rule-id") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "rule não encontrado") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RuleMarketPlaceDTO> find(@PathVariable Long id);
	
	@Override
	@ApiOperation(value="Busca por todas rules") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "nenhum rule encontrada") })
	@GetMapping()
	public ResponseEntity<List<RuleMarketPlaceDTO>> findAll();

	//TODO descomentar quando for implementado spring security
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@Override
	@ApiOperation(value="Alterar Rule")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<RuleMarketPlaceDTO> update(@Valid @RequestBody RuleMarketPlaceDTO objDTO, @PathVariable Long id);


}

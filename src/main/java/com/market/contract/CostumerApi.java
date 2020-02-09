package com.market.contract;

import java.util.List;

import javax.validation.Valid;

import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.RuleMarketPlaceDTO;
import com.market.contract.dto.filters.CostumerFiltersDTO;
import com.market.contract.dto.filters.RuleMarketPlaceFiltersDTO;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.market.contract.dto.CostumerDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api/v1/costumers/")
public interface CostumerApi extends GenericApi<CostumerDTO, CostumerFiltersDTO> {
	
	@Override
	@ApiOperation(value="Inclusão de custumer")
	@PostMapping()
	public ResponseEntity<Void> save(@Valid @RequestBody CostumerDTO dto);
	
	@Override
	@ApiOperation(value="Busca por custumer-id") 
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "custumer não encontrado") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CostumerDTO> find(@PathVariable Long id);
	
	@Override
	@ApiOperation(value="Busca por todos costumer")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "nenhum costumer encontrado") })
	@GetMapping
	public ResponseEntity<List<CostumerDTO>> findAll();

	@Override
	@ApiOperation(value="Alterar por costumer-id")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "custumer não encontrado") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CostumerDTO> update(final @Valid @RequestBody CostumerDTO dto, Long id);

	@ApiOperation(value="Busca De Costumers Paginada")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "nenhum cliente encontrado") })
	@GetMapping("/paginate")
	ResponseEntity<PaginatedResourceDTO<CostumerDTO>> findPaginate(@SpringQueryMap @Valid final CostumerFiltersDTO filters);
}

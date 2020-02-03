package com.market.contract;

import com.market.contract.dto.ProductDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/products/")
public interface ProductApi extends GenericApi<ProductDTO> {

    @Override
    @ApiOperation(value="Inclusão de product")
    @PostMapping()
    public ResponseEntity<Void> save(@Valid @RequestBody ProductDTO dto);

    @Override
    @ApiOperation(value="Busca por product-id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Product não encontrado") })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> find(@PathVariable Long id);

    @Override
    @ApiOperation(value="Busca por todos product")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Nenhum product encontrado") })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll();

    @Override
    @ApiOperation(value="Alterar por product-id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Product não encontrado") })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDTO> update(final @Valid @RequestBody ProductDTO dto, Long id);

}

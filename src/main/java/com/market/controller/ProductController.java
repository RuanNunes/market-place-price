package com.market.controller;

import com.market.contract.ProductApi;
import com.market.contract.dto.ProductDTO;
import com.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Valid
@RestController
public class ProductController implements ProductApi {

    @Autowired
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> save(@RequestBody @Valid ProductDTO dto) {
        final var obj = service.save(dto);
        //retorna url com novo registro inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ProductDTO> find(Long id) { return ResponseEntity.ok().body(service.find(id)); }

    @Override
    public ResponseEntity<List<ProductDTO>> findAll() { return ResponseEntity.ok().body(service.findAll()); }

    @Override
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto, Long id) { return ResponseEntity.ok().body(service.update(dto, id)); }
}

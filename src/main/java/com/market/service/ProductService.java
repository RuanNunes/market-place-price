package com.market.service;

import com.market.contract.dto.CostumerDTO;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.ProductDTO;
import com.market.mapper.ProductMapper;
import com.market.model.Product;
import com.market.repository.ProductRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements GenericService<ProductDTO, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(final ProductDTO dto) {
        if((dto.getId() != null))
            dto.setId(null);

        final Product entity = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(entity));
    }

    @Override
    public List<ProductDTO> findAll(){
        final var products = productRepository.findAll();

        if(products.isEmpty())
            new ObjectNotFoundException("Não existe produtos cadastrados");

        return productMapper.toDto(products);
    }

    @Override
    public ProductDTO find(final Long id) {

        Optional<Product> obj = productRepository.findById(id);

        return productMapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Product.class.getName())));
    }

    @Override
    public ProductDTO update(final ProductDTO dto, final Long id) {
        final Product entity = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException.supply());
        final Product updateEntity = productMapper.updateEntity(entity, dto);
        final Product updatedEntity = productRepository.save(updateEntity);
        return productMapper.toDto(updatedEntity);
    }

    @Override
    public PaginatedResourceDTO<ProductDTO> findPaginate(ProductDTO filter) {
        return null;
    }
}

package com.market.mapper;

import com.market.contract.dto.ProductDTO;
import com.market.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends GenericMapper<Product, ProductDTO> {

    @Override
    public Product updateEntity(Product product, ProductDTO productDTO) {
        if (productDTO.getId() == null || productDTO.getId() == 0)
            productDTO.setId(product.getId());

        return super.updateEntity(product, productDTO);
    }

}

package com.market.service;

import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.ProductDTO;
import com.market.contract.dto.filters.ProductFiltersDTO;
import com.market.mapper.ProductMapper;
import com.market.model.Customer;
import com.market.model.Product;
import com.market.model.RuleMarketPlace;
import com.market.repository.CustomerRepository;
import com.market.repository.ProductRepository;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements GenericService<ProductDTO, ProductFiltersDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CustomerRepository costumerRepository;

    @Autowired
    private RuleMarketPlaceRepository ruleMarketPlaceRepository;

    @Override
    public ProductDTO save(final ProductDTO dto) {
        if(dto.getId() != null || dto.getId() == 0)
            dto.setId(null);


        final Product entity = productMapper.toEntity(dto);

        if (dto.getRulesId() != null || !dto.getRulesId().isEmpty()){
            final var rules = new HashSet<RuleMarketPlace>();
            for (Long idRule : dto.getRulesId()) {
                rules.add(ruleMarketPlaceRepository.findById(idRule)
                        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+idRule+", Tipo: "+ RuleMarketPlace.class.getName())));
            }
            entity.setRules(rules);
        }

        final Customer costumer = costumerRepository.findById(dto.getCostumerId())
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+dto.getCostumerId()+", Tipo: "+ Customer.class.getName()));

        entity.setCostumer(costumer);
        final var productSave = productRepository.save(entity);
        return productMapper.toDto(productSave);
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

        return productMapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!" +
                " Id: "+id+", Tipo: "+ Product.class.getName())));
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
    public PaginatedResourceDTO<ProductDTO> findPaginate(ProductFiltersDTO filter) {
        return null;
    }

//    @Override
//    public PaginatedResourceDTO<ProductDTO> findPaginate(final ProductFiltersDTO filters,
//                                                         final RuleMarketPlaceFiltersDTO ruleFilters) {
//
//        final var sortMapping = ImmutableMap.<ProductSortDTO, Sort>builder()
//                .put(ProductSortDTO.MOST_RECENT, Sort.by("created_date").descending())
//                .put(ProductSortDTO.LEAST_RECENT, Sort.by("created_date").ascending())
//                .build();
//
//        final Sort sort = sortMapping.get(filters.getSorter());
//        final PageRequest pageRequest = PageRequest.of(filters.getPage(), filters.getLimit(), sort);
//
//        final var teste = productRepository.findProducts(filters.getName(), pageRequest);
//
//        return productMapper.toDto(teste);
   // }
}

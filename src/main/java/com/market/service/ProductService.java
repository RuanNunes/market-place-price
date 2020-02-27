package com.market.service;

import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.ProductDTO;
import com.market.contract.dto.filters.ProductFiltersDTO;
import com.market.mapper.ProductMapper;
import com.market.model.Costumer;
import com.market.model.Product;
import com.market.repository.CostumerRepository;
import com.market.repository.ProductRepository;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements GenericService<ProductDTO, ProductFiltersDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private RuleMarketPlaceRepository ruleMarketPlaceRepository;

    @Override
    public ProductDTO save(final ProductDTO dto) {
        final Product entity = productMapper.toEntity(dto);
        if(dto.getId() != null || dto.getId() == null || dto.getId() == 0)
            entity.setId(null);

        //TODO descomentar se adicionar ligação entre product e rule
//        if (dto.getRulesId() != null || !dto.getRulesId().isEmpty()){
//            final var rules = new HashSet<RuleMarketPlace>();
//            for (Long idRule : dto.getRulesId()) {
//                rules.add(ruleMarketPlaceRepository.findById(idRule)
//                        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+idRule+", Tipo: "+ RuleMarketPlace.class.getName())));
//            }
//            entity.setRules(rules);
//        }

        final Costumer costumer = costumerRepository.findById(dto.getCostumerId())
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+dto.getCostumerId()+", Tipo: "+ Costumer.class.getName()));

        entity.setCustomer(costumer);
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

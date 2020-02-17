package com.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.RuleMarketPlaceDTO;
import com.market.contract.dto.filters.RuleMarketPlaceFiltersDTO;
import com.market.contract.dto.filters.enuns.RuleMarketPlaceSortDTO;
import com.market.mapper.RuleMarketPlaceMapper;
import com.market.model.Customer;
import com.market.model.RuleMarketPlace;
import com.market.repository.CustomerRepository;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;

@Service
public class RuleMarketPlaceService implements GenericService<RuleMarketPlaceDTO, RuleMarketPlaceFiltersDTO>{
	@Autowired
	private RuleMarketPlaceRepository ruleMarketPlaceRepository;
	
	@Autowired 
	private RuleMarketPlaceMapper mapper;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public RuleMarketPlaceDTO save(final RuleMarketPlaceDTO dto) {
		final RuleMarketPlace entity = mapper.toEntity(dto);

		if(!(entity.getId() == null)) 
			entity.setId(null);

		final Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new ObjectNotFoundException(    "Objeto não encontrado! Id: "
				+ dto.getCustomerId() + ", Tipo: " + Customer.class.getName()));

		entity.setCustomer(customer);
		return mapper.toDto(ruleMarketPlaceRepository.save(entity));
	}
	
	@Override
	public List<RuleMarketPlaceDTO> findAll(){
		final var rules = ruleMarketPlaceRepository.findAll();
		
		if(rules.isEmpty())
			new ObjectNotFoundException("Não existe Regras cadastradas");
		
		return mapper.toDto(rules);
	}
	
	@Override
	public RuleMarketPlaceDTO find(Long id) {
		
		final Optional<RuleMarketPlace> entity = ruleMarketPlaceRepository.findById(id);

		return mapper.toDto(entity.orElseThrow(() -> new ObjectNotFoundException(    "Objeto não encontrado! Id: " 
				+ id + ", Tipo: " + RuleMarketPlace.class.getName())));
	}
	
	@Override
	public RuleMarketPlaceDTO update(final RuleMarketPlaceDTO dto, final Long id) {
		final RuleMarketPlace entity =
				 ruleMarketPlaceRepository.findById(id).orElseThrow(ResourceNotFoundException.supply());

		final RuleMarketPlace updateEntity = mapper.updateEntity(entity, dto);
		final RuleMarketPlace updatedEntity = ruleMarketPlaceRepository.save(updateEntity);
		return mapper.toDto(updatedEntity);
	  }

	// Pesquisa paginada falta definir todos filtros
	@Override
	public PaginatedResourceDTO<RuleMarketPlaceDTO> findPaginate(final RuleMarketPlaceFiltersDTO filters) {
		final var sortMapping = ImmutableMap.<RuleMarketPlaceSortDTO, Sort>builder()
				.put(RuleMarketPlaceSortDTO.MOST_RECENT, Sort.by("created_date").descending())
				.put(RuleMarketPlaceSortDTO.LEAST_RECENT, Sort.by("created_date").ascending())
				.build();

		final Sort sort = sortMapping.get(filters.getSorter());
		final PageRequest pageRequest = PageRequest.of(filters.getPage(), filters.getLimit(), sort);

//		final var rules = ruleMarketPlaceRepository.findDistinctByNameContaining(filters.getName(), pageRequest);
		final var teste = ruleMarketPlaceRepository.findRules(filters.getName(), pageRequest);
		return mapper.toDto(teste);
	}
}

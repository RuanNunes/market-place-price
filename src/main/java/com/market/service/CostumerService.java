package com.market.service;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.filters.CostumerFiltersDTO;
import com.market.contract.dto.filters.enuns.BaseSortDTO;
import com.market.contract.dto.filters.enuns.RuleMarketPlaceSortDTO;
import com.market.model.RuleMarketPlace;
import com.market.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.market.contract.dto.CostumerDTO;
import com.market.mapper.CostumerMapper;
import com.market.model.Costumer;
import com.market.repository.CostumerRepository;
import com.market.service.exception.ObjectNotFoundException;

@Service
public class CostumerService implements GenericService<CostumerDTO, CostumerFiltersDTO>{
	@Autowired
	private CostumerRepository costumerRepository;
	
	@Autowired
	private CostumerMapper mapper;
	
	@Override
	public CostumerDTO save(final CostumerDTO dto) {
		if((dto.getId() != null)) 
			dto.setId(null);
		
		final Costumer entity = mapper.toEntity(dto);
		return mapper.toDto(costumerRepository.save(entity));
	}
	
	@Override
	public List<CostumerDTO> findAll(){
		final var custumers = costumerRepository.findAll();
		
		if(custumers.isEmpty())
			new ObjectNotFoundException("Não existe usuarios cadastrados");
		
		return mapper.toDto(custumers);
	}
	
	@Override
	public CostumerDTO find(Long id) {
		
		//TODO descomentar quando implementação de spring security for configurada
//		UserSS user = UserService.authenticated();
//		
//		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado.");
//		}
		
		Optional<Costumer> obj = costumerRepository.findById(id);
		
		return mapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Costumer.class.getName()))); 
	}

	@Override
	public CostumerDTO update(final CostumerDTO dto, final Long id) {
		final Costumer entity = costumerRepository.findById(id)
				.orElseThrow(ResourceNotFoundException.supply());
		final Costumer updateEntity = mapper.updateEntity(entity, dto);
		final Costumer updatedEntity = costumerRepository.save(updateEntity);
		return mapper.toDto(updatedEntity);
	}

	@Override
	public PaginatedResourceDTO<CostumerDTO> findPaginate(CostumerFiltersDTO filter) {
		final var sortMapping = ImmutableMap.<BaseSortDTO, Sort>builder()
				.put(BaseSortDTO.MOST_RECENT, Sort.by("created_date").descending())
				.put(BaseSortDTO.LEAST_RECENT, Sort.by("created_date").ascending())
				.build();

		final Sort sort = sortMapping.get(filter.getSorter());
		final PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getLimit(), sort);

		final var costumers = costumerRepository.findCostumers(filter.getName(), pageRequest);
		return mapper.toDto(costumers);
	}
}

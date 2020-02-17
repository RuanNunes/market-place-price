package com.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.market.contract.dto.CostumerDTO;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.filters.CustomerFiltersDTO;
import com.market.contract.dto.filters.enuns.BaseSortDTO;
import com.market.mapper.CostumerMapper;
import com.market.model.Customer;
import com.market.repository.CustomerRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;

@Service
public class CostumerService implements GenericService<CostumerDTO, CustomerFiltersDTO>{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CostumerMapper mapper;
	
	@Override
	public CostumerDTO save(final CostumerDTO dto) {
		if((dto.getId() != null)) 
			dto.setId(null);
		
		final Customer entity = mapper.toEntity(dto);
		return mapper.toDto(customerRepository.save(entity));
	}
	
	@Override
	public List<CostumerDTO> findAll(){
		final var custumers = customerRepository.findAll();
		
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
		
		Optional<Customer> obj = customerRepository.findById(id);
		
		return mapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+ Customer.class.getName())));
	}

	@Override
	public CostumerDTO update(final CostumerDTO dto, final Long id) {
		final Customer entity = customerRepository.findById(id)
				.orElseThrow(ResourceNotFoundException.supply());
		final Customer updateEntity = mapper.updateEntity(entity, dto);
		final Customer updatedEntity = customerRepository.save(updateEntity);
		return mapper.toDto(updatedEntity);
	}

	@Override
	public PaginatedResourceDTO<CostumerDTO> findPaginate(CustomerFiltersDTO filter) {
		final var sortMapping = ImmutableMap.<BaseSortDTO, Sort>builder()
				.put(BaseSortDTO.MOST_RECENT, Sort.by("created_date").descending())
				.put(BaseSortDTO.LEAST_RECENT, Sort.by("created_date").ascending())
				.build();

		final Sort sort = sortMapping.get(filter.getSorter());
		final PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getLimit(), sort);

		final var costumers = customerRepository.findCustomers(filter.getName(), pageRequest);
		return mapper.toDto(costumers);
	}
}

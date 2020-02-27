package com.market.service;

import com.google.common.collect.ImmutableMap;
import com.market.contract.dto.CostumerDTO;
import com.market.contract.dto.PaginatedResourceDTO;
import com.market.contract.dto.filters.CustomerFiltersDTO;
import com.market.contract.dto.filters.enuns.BaseSortDTO;
import com.market.mapper.CostumerMapper;
import com.market.model.Costumer;
import com.market.repository.CostumerRepository;
import com.market.service.exception.ObjectNotFoundException;
import com.market.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService implements GenericService<CostumerDTO, CustomerFiltersDTO>{
	@Autowired
	private CostumerRepository customerRepository;
	
	@Autowired
	private CostumerMapper mapper;

//	@Autowired
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public CostumerDTO save(final CostumerDTO dto) {
		if((dto.getId() != null)) 
			dto.setId(null);
		
		final Costumer entity = mapper.toEntity(dto);
		entity.setPassword(encoder.encode(entity.getPassword()));
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
		
		Optional<Costumer> obj = customerRepository.findById(id);
		
		return mapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+ Costumer.class.getName())));
	}

	@Override
	public CostumerDTO update(final CostumerDTO dto, final Long id) {
		final Costumer entity = customerRepository.findById(id)
				.orElseThrow(ResourceNotFoundException.supply());
		final Costumer updateEntity = mapper.updateEntity(entity, dto);
		final Costumer updatedEntity = customerRepository.save(updateEntity);
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

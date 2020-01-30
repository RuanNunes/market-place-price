package com.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.contract.dto.RuleMarketPlaceDTO;
import com.market.mapper.RuleMarketPlaceMapper;
import com.market.model.RuleMarketPlace;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.service.exception.ObjectNotFoundException;

@Service
public class RuleMarketPlaceService implements GenericService<RuleMarketPlaceDTO>{
	@Autowired
	private RuleMarketPlaceRepository ruleMarketPlaceRepository;
	
	@Autowired 
	private RuleMarketPlaceMapper mapper;
	
	@Override
	public RuleMarketPlaceDTO save(final RuleMarketPlaceDTO dto) {
		final RuleMarketPlace entity = mapper.toEntity(dto);

		if(!(entity.getId() == null)) 
			entity.setId(null);
		
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
	    final RuleMarketPlaceDTO findDto = find(id);
	    final RuleMarketPlaceDTO updatedDto = mapper.updateDto(findDto, dto);
	    return mapper.toDto(ruleMarketPlaceRepository.save(mapper.toEntity(updatedDto)));
	  }
}

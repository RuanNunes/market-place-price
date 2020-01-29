package com.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.contract.dto.CostumerDTO;
import com.market.mapper.CostumerMapper;
import com.market.model.Costumer;
import com.market.repository.CustumerRepository;
import com.market.service.exception.ObjectNotFoundException;

@Service
public class CustumerService implements GenericService<CostumerDTO>{
	@Autowired
	private CustumerRepository custumerRepository;
	
	@Autowired
	private CostumerMapper mapper;
	
	@Override
	public CostumerDTO save(final CostumerDTO dto) {
		if((dto.getId() != null)) 
			dto.setId(null);
		
		final Costumer entity = mapper.toEntity(dto);
		return mapper.toDto(custumerRepository.save(entity));
	}
	
	@Override
	public List<CostumerDTO> findAll(){
		final var custumers = custumerRepository.findAll();
		
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
		
		Optional<Costumer> obj = custumerRepository.findById(id);
		
		return mapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Costumer.class.getName()))); 
	}

	@Override
	public CostumerDTO update(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

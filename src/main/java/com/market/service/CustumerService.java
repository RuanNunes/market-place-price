package com.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.contract.dto.CustumerDTO;
import com.market.mapper.CustumerMapper;
import com.market.model.Custumer;
import com.market.repository.CustumerRepository;
import com.market.service.exception.ObjectNotFoundException;

@Service
public class CustumerService {
	@Autowired
	private CustumerRepository custumerRepository;
	
	@Autowired
	private CustumerMapper mapper;
	
	public CustumerDTO save(final CustumerDTO dto) {
		if(!(dto.getId() == null)) 
			dto.setId(null);
		
		final Custumer entity = mapper.toEntity(dto);
		return mapper.toDto(custumerRepository.save(entity));
	}
	
	public List<CustumerDTO> findAll(){
		final var custumers = custumerRepository.findAll();
		
		if(custumers.isEmpty())
			new ObjectNotFoundException("Não existe usuarios cadastrados");
		
		return mapper.toDto(custumers);
	}
	
	public CustumerDTO find(Long id) {
		
		//TODO descomentar quando implementação de spring security for configurada
//		UserSS user = UserService.authenticated();
//		
//		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado.");
//		}
		
		Optional<Custumer> obj = custumerRepository.findById(id);
		
		//orElseThrow recebe função que instancia uma exception customizada utilizando uma expressão lambda
		return mapper.toDto(obj.orElseThrow(() -> new ObjectNotFoundException(    "Objeto não encontrado! Id: " 
									+ id + ", Tipo: " + Custumer.class.getName()))); 
	}

}

package com.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.model.Custumer;
import com.market.repository.CustumerRepository;
import com.market.service.exception.ObjectNotFoundException;

@Service
public class CustumerService {
	@Autowired
	private CustumerRepository custumerRepository;
	
	public Custumer save(final Custumer custumer) {
		if(!(custumer.getId() == null)) 
			custumer.setId(null);
		
		return custumerRepository.save(custumer);
	}
	
	public List<Custumer> findAll(){
		final var custumers = custumerRepository.findAll();
		
		if(custumers.isEmpty())
			new ObjectNotFoundException("Não existe usuarios cadastrados");
		
		return custumers;
	}
	
	public Custumer find(Integer id) {
		
		//TODO descomentar quando implementação de spring security for configurada
//		UserSS user = UserService.authenticated();
//		
//		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado.");
//		}
		
		Optional<Custumer> obj = custumerRepository.findById(id);
		//orElseThrow recebe função que instancia uma exception customizada utilizando uma expressão lambda
		return obj.orElseThrow(() -> new ObjectNotFoundException(    "Objeto não encontrado! Id: " 
									+ id + ", Tipo: " + Custumer.class.getName())); 
	}

}

package com.market.service;

import com.market.model.Costumer;
import com.market.repository.CostumerRepository;
import com.market.security.models.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CostumerRepository costumerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Costumer costumer = costumerRepository.findByEmail(email);
		if(costumer == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(costumer.getId(), costumer.getEmail(), costumer.getPassword(), costumer.getProfiles());
	}

}

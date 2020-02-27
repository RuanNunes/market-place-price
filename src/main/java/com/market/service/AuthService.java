package com.market.service;

import com.market.model.Costumer;
import com.market.repository.CostumerRepository;
import com.market.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
	
	@Autowired
	private CostumerRepository costumerRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
//	@Autowired
//	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		final Costumer costumer = costumerRepository.findByEmail(email);
		
		if(costumer == null)
			throw new ObjectNotFoundException("Email não encontrado");
		
		final String newPass = newPassWord();
		costumer.setPassword(encoder.encode(newPass));

		costumerRepository.save(costumer);
//		emailService.sendNewPasswordEmail(cliente, newPass);
		
	}

	private String newPassWord() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) // gera um digito
			return (char) (rand.nextInt(10) + 48);
		
		if(opt == 1) // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		
		return (char) (rand.nextInt(26) + 97); // gera letra minuscula
	}
	
	
}
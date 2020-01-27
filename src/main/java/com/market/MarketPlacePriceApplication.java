package com.market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.market.contract.dto.RuleMarketPlaceDTO;
import com.market.model.RuleMarketPlace;
import com.market.repository.RuleMarketPlaceRepository;
import com.market.service.RuleMarketPlaceService;

@SpringBootApplication
public class MarketPlacePriceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MarketPlacePriceApplication.class, args);
	}
	@Autowired
	private RuleMarketPlaceService rule;
	
	@Autowired
	private RuleMarketPlaceRepository ruleRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		//saveUmAUm();
		//saveInPackage();
	}
	
	private void saveUmAUm() {
		System.out.println("Incluindo um a um");
		Long mileInic = System.currentTimeMillis();
		int pacote = 10000;
		for (int i = 0; i < pacote; i++) {
			String uid = UUID.randomUUID().toString();
			
			rule.save(RuleMarketPlaceDTO.builder()
					.name("Rule " + i + " " + uid)
					.description("Descrição da rule "+i)
					.discountPercentage(BigDecimal.valueOf(16))
					.build());
		}
		Long mileFin = System.currentTimeMillis();
		Long time = ((mileFin - mileInic)/1000)/60;
		System.out.println(time + " Minutos para inserir um a um......> Total Inclusos= "+ pacote);
	}
	
	private void saveInPackage() {
		
		Long mileInic = System.currentTimeMillis();
		int pacote = 10000;
		System.out.println("Incluindo "+pacote+ " a " + pacote);
		for (int i = 0; i < 10; i++) {
			final List<RuleMarketPlace> rules = new ArrayList<RuleMarketPlace>();
			for (int j = 0; j < pacote; j++) {
				
				String uid = UUID.randomUUID().toString();
				rules.add(RuleMarketPlace.builder()
						.name("Rule " + i + " " + uid)
						.description("Descrição da rule "+i)
						.discountPercentage(BigDecimal.valueOf(16))
						.build());
			}
			ruleRepository.saveAll(rules);
		}
		
		Long mileFin = System.currentTimeMillis();
		Long time = ((mileFin - mileInic)/1000)/60;
		System.out.println(time + " Minutos para inserir "+ pacote + " a " 
		+ pacote+"......>Total Inclusos= "+ pacote*100);
	}
}

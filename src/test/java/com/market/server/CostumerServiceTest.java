package com.market.server;

import com.market.model.Costumer;
import com.market.repository.CostumerRepository;
import com.market.service.CostumerService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class CostumerServiceTest {
    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private CostumerService costumerService;

    private void saveCostumerInPacage(int pacote, int repeticoes){
        Long mileInic = System.currentTimeMillis();

        System.out.println("Incluindo Costumers "+pacote+ " a " + pacote);
        for (int i = 0; i < repeticoes; i++) {
            final List<Costumer> costumers = new ArrayList<Costumer>();
            for (int j = 0; j < pacote; j++) {

                String uid = UUID.randomUUID().toString();
                costumers.add(Costumer.builder()
                        .name("Rule " + i + " " + uid)
                        .category("Premium")
                        .email("teste."+uid+"@gmail.com")
                        .level("Iniciante")
                        .password(uid)
                        .build());
            }
            costumerRepository.saveAll(costumers);
        }

        Long mileFin = System.currentTimeMillis();
        Long time = ((mileFin - mileInic)/1000)/60;
        System.out.println(time + " Minutos para inserir "+ pacote + " a "
                + pacote+"......>Total Costumer Inclusos= "+ pacote*repeticoes);
    }
}

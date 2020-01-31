package com.market.mapper;

import org.springframework.stereotype.Component;

import com.market.contract.dto.CostumerDTO;
import com.market.model.Costumer;

@Component
public class CostumerMapper extends GenericMapper<Costumer, CostumerDTO>{

    @Override
    public Costumer updateEntity(Costumer costumer, CostumerDTO costumerDTO) {
        if (costumerDTO.getId() == null || costumerDTO.getId() == 0)
            costumerDTO.setId(costumer.getId());

        return super.updateEntity(costumer, costumerDTO);
    }
}

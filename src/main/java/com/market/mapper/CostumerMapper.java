package com.market.mapper;

import org.springframework.stereotype.Component;

import com.market.contract.dto.CostumerDTO;
import com.market.model.Customer;

@Component
public class CostumerMapper extends GenericMapper<Customer, CostumerDTO>{

    @Override
    public Customer updateEntity(Customer costumer, CostumerDTO costumerDTO) {
        if (costumerDTO.getId() == null || costumerDTO.getId() == 0)
            costumerDTO.setId(costumer.getId());

        return super.updateEntity(costumer, costumerDTO);
    }
}

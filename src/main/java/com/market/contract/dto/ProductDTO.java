package com.market.contract.dto;

import com.market.model.Costumer;
import com.market.model.RuleMarketPlace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    private Costumer costumer;
    @NotNull
    private RuleMarketPlace ruleMarketPlaceId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String sku;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal costPrice;

}

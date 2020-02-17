package com.market.contract.dto.filters;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.market.contract.dto.filters.enuns.ProductSortDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductFiltersDTO {

    @Builder.Default
    private String name = "";

    @Builder.Default
    private ProductSortDTO sorter = ProductSortDTO.MOST_RECENT;

    @Min(0)
    @Builder.Default
    private Integer page = 0;

    @Min(1)
    @Max(50)
    @Builder.Default
    private Integer limit = 0;

    @Builder.Default
    private Set<Long> rulesId = new HashSet<>();

    public static ProductFiltersDTO of() { return ProductFiltersDTO.builder().build(); }

}

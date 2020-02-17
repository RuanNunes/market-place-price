package com.market.contract.dto.filters;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.market.contract.dto.filters.enuns.BaseSortDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFiltersDTO {

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String transactionAdditionalData = "{}";

    @Builder.Default
    private BaseSortDTO sorter = BaseSortDTO.MOST_RECENT;

    @Min(0)
    @Builder.Default
    private Integer page = 0;

    @Min(1)
    @Max(50)
    @Builder.Default
    private Integer limit = 10;

    public static CustomerFiltersDTO of() {
        return CustomerFiltersDTO.builder().build();
    }
}

package com.market.contract.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.market.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleMarketPlaceDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private BigDecimal discountPercentage;
	
	private Integer defaultShippingValue;
	
	private BigDecimal flatRate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private LocalDateTime createdDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	private LocalDateTime lastModifiedDate;

	//faz com que omite a busca de produtos pois ja foi serializado do lado de produtos
	@JsonIgnore
	private List<ProductDTO> products = new ArrayList<>();
}

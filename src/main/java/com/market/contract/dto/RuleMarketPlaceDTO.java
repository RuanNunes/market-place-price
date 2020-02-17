package com.market.contract.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Long customerId;
	
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
	//TODO descomentar se futuramente ouver ligação entre rule e product
//	@JsonIgnore
//	private List<ProductDTO> products = new ArrayList<>();
}

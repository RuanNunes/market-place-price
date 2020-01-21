package com.market.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class RuleMarketPlace extends PersistentEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	 
	@Column(nullable = false, unique = true)
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private BigDecimal discountPercentage;
	
	
	private Integer defaultShippingValue;
	
	private BigDecimal flatRate;


}

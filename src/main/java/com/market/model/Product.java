package com.market.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Product extends PersistentEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "custumer_id")
	@NotNull
	private Costumer custumerId;
	@OneToOne
	@JoinColumn(name = "rule_market_place_id")
	private RuleMarketPlace ruleMarketPlaceId;
	@NotNull
	private String name;
	private String description;
	private String sku;
	private BigDecimal price;
	@NotNull
	private BigDecimal costPrice;
	

	
	
}

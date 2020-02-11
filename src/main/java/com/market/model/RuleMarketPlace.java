package com.market.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
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

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@NotNull
	private BigDecimal discountPercentage;

	private Integer defaultShippingValue;

	private BigDecimal flatRate;

	@ManyToMany( mappedBy = "rules")
	private List<Product> products = new ArrayList<>();
}

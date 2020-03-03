package com.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "sequence_generator", sequenceName = "SEQ_RULE_MARKET_PLACE", allocationSize = 1)
public class RuleMarketPlace extends PersistentEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(length = 80)
	private String name;

	@NotNull
	private String description;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Costumer customer;

	@NotNull
	private BigDecimal discountPercentage;

	private Integer defaultShippingValue;

	private BigDecimal flatRate;

	//TODO validar futuramente se existe necessidade de aver ligação com product
//	@ManyToMany( mappedBy = "rules")
//	private List<Product> products = new ArrayList<>();
}

package com.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@SequenceGenerator(name = "sequence_generator", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
public class Product extends PersistentEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Costumer customer;


	//Cria tabela auxiliar para fazer ligação de muitos para muitos entre rule e produtos
	//TODO analizar futuramente se precisa de ligação entre rule e product
//	@ManyToMany
//	@JoinTable(name = "product_rule",
//			joinColumns = @JoinColumn(name = "product_id"),
//			inverseJoinColumns = @JoinColumn(name = "rule_id" )
//	)
//	private Set<RuleMarketPlace> rules = new HashSet<>();

	@NotNull
	@Column(length = 80)
	private String name;
	private String description;
	@Column(length = 8)
	private String sku;
	private BigDecimal price;
	@NotNull
	private BigDecimal costPrice;
}

package com.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@SequenceGenerator(name = "sequence_generator", sequenceName = "SEQ_COSTUMER", allocationSize = 1)
public class Costumer extends PersistentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(length = 80)
	private String name;

	@NotNull
	@Column(length = 100)
	private String email;
	@NotNull
	private String password;
	
	//TODO faz parte das rules
	@NotNull
	@Column(length = 30)
	private String level;
	
	@NotNull
	@Column(length = 30)
	private String category;

	@OneToMany(mappedBy = "customer")
	private List<RuleMarketPlace> rules = new ArrayList<>();

	@OneToMany(mappedBy = "customer")
	private List<Product> products = new ArrayList<>();
}

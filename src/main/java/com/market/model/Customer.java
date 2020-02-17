package com.market.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
public class Customer extends PersistentEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotNull
	private String name;

	@NotNull
	private String email;
	@NotNull
	private String password;
	
	//TODO faz parte das rules
	@NotNull
	private String level;
	
	@NotNull
	private String category;

	@OneToMany(mappedBy = "customer")
	private List<RuleMarketPlace> rules = new ArrayList<>();

	@OneToMany(mappedBy = "customer")
	private List<Product> products = new ArrayList<>();
}

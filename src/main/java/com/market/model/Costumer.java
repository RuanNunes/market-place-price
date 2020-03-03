package com.market.model;

import com.market.security.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

	//faz com que sempre que trouxer um cliente vai carregar o perfil junto
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "profiles")
	private Set<Integer> profiles = new HashSet<>();

	public Set<Profile> getProfiles(){
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
}

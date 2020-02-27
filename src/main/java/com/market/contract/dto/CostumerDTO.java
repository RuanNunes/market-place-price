package com.market.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull
	private String email;
	@NotNull
	private String name;
	@NotNull
	private String password;
	
	//TODO faz parte das rules
	@NotNull
	private String level;
	
	@NotNull
	private String category;

}

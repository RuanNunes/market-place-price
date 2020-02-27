package com.market.contract.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

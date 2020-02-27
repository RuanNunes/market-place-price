package com.market.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class EmailDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento Obrigatorio.")
	@Email(message = "Email obrigatório.")
	private String email;
}

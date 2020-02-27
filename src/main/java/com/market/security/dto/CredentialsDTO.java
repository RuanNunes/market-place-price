package com.market.security.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private String password;




}
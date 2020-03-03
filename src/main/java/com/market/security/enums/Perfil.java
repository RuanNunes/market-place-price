package com.market.security.enums;

import lombok.Getter;
import lombok.Setter;

public enum Perfil {
    //ROLE É EXIGENCIA DO SPRING SECURITY
    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENTE");

    @Getter
    @Setter
    private int cod;

    @Getter
    @Setter
    private String descricao;

    private Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("id invalido: " + cod);
    }
}
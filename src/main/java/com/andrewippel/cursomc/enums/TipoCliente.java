package com.andrewippel.cursomc.enums;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private Integer cod;
    private String descricao;

    TipoCliente(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente getEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoCliente t : TipoCliente.values()) {
            if (cod.equals(t.getCod())) {
                return t;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}

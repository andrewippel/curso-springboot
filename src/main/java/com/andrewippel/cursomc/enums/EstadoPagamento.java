package com.andrewippel.cursomc.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer cod;
    private String descricao;

    EstadoPagamento(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento getEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (EstadoPagamento t : EstadoPagamento.values()) {
            if (cod.equals(t.getCod())) {
                return t;
            }
        }

        throw new IllegalArgumentException("Estado do pagamento inválido: " + cod);
    }
}

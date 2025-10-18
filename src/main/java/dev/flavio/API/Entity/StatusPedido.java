// StatusPedido.java
package dev.flavio.API.Entity;

public enum StatusPedido {
    PENDENTE("Pendente"),
    PROCESSANDO("Processando"),
    ENVIADO("Enviado"),
    ENTREGUE("Entregue"),
    EXTRAVIADO("Extravidado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
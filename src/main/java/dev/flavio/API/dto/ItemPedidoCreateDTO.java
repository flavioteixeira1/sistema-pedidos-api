package dev.flavio.API.dto;

public class ItemPedidoCreateDTO {
    private Long produtoId;
    private Integer quantidade;

    // Construtores
    public ItemPedidoCreateDTO() {}

    public ItemPedidoCreateDTO(Long produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
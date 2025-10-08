package dev.flavio.API.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
    private Long id;
    private ProdutoDTO produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemPedidoDTO() {}

    public ItemPedidoDTO(Long id, ProdutoDTO produto, Integer quantidade, 
                        BigDecimal precoUnitario, BigDecimal subtotal) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ProdutoDTO getProduto() { return produto; }
    public void setProduto(ProdutoDTO produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
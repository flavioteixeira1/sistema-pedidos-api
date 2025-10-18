package dev.flavio.API.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import dev.flavio.API.Entity.StatusPedido;

public class PedidoDTO {
    private Long id;
    private String endereco;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private String observacoes;
    
    private BigDecimal total;
    private Set<ItemPedidoDTO> itens; // Mudou de Set<ProdutoDTO> para Set<ItemPedidoDTO>
    private ClienteResumoDTO cliente;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Set<ItemPedidoDTO> getItens() { return itens; }
    public void setItens(Set<ItemPedidoDTO> itens) { this.itens = itens; }

    public ClienteResumoDTO getCliente() { return cliente; }
    public void setCliente(ClienteResumoDTO cliente) { this.cliente = cliente; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }  
}
package dev.flavio.API.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dev.flavio.API.Entity.StatusPedido;

public class PedidoResumoDTO {
    private Long id;
    private String endereco;
    private LocalDateTime dataPedido;
    private Enum<StatusPedido> status;
    private BigDecimal total; // Adicionar campo total

    public PedidoResumoDTO() {}

    public PedidoResumoDTO(Long id, String endereco, LocalDateTime dataPedido, StatusPedido status, BigDecimal total) {
        this.id = id;
        this.endereco = endereco;
        this.dataPedido = dataPedido;
        this.status = status;
        this.total = total;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public Enum<StatusPedido> getStatus() { return status; }
    public void setStatus(Enum<StatusPedido> status) { this.status = status; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
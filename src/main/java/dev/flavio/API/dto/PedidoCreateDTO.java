package dev.flavio.API.dto;

import java.util.Set;

public class PedidoCreateDTO {
    private String endereco;
    private String status;
    private Long clienteId;
    private Set<ItemPedidoCreateDTO> itens; // Mudou de Set<Long> para Set<ItemPedidoCreateDTO>

    // Getters e Setters
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Set<ItemPedidoCreateDTO> getItens() { return itens; }
    public void setItens(Set<ItemPedidoCreateDTO> itens) { this.itens = itens; }
}
// PedidoUpdateDTO.java
package dev.flavio.API.dto;

import java.util.Set;

public class PedidoUpdateDTO {
    private String endereco;
    private String status;
    private Long clienteId;
    private Set<ItemPedidoCreateDTO> itens;

    // Getters e Setters
    public String  getendereco(){
        return this.endereco;
    }

    public String  getstatus(){
        return this.status;
    }

    public Long  getclienteId(){
        return this.clienteId;
    }

    public Set<ItemPedidoCreateDTO> getItens() { return itens; }
    public void setItens(Set<ItemPedidoCreateDTO> itens) { this.itens = itens; }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

     public void setstatus(String status) {
        this.status = status;
    }

    public void setclienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    

}
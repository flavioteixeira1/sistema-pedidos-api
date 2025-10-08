package dev.flavio.API.Mapper;

import dev.flavio.API.dto.*;
import dev.flavio.API.Entity.Pedido;
import dev.flavio.API.Entity.ItemPedido;
import dev.flavio.API.Entity.Produto;
import dev.flavio.API.Entity.Clientes;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) return null;

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setEndereco(pedido.getEndereco());
        dto.setDataPedido(pedido.getDataPedido());
        dto.setStatus(pedido.getStatus());
        dto.setTotal(pedido.getTotal()); // Adicionar o total calculado

        // Converter itens do pedido (produtos com quantidade)
        if (pedido.getItens() != null) {
            dto.setItens(pedido.getItens().stream()
                    .map(PedidoMapper::itemPedidoToDTO)
                    .collect(Collectors.toSet()));
        }

        // Converter cliente (apenas dados resumidos)
        if (pedido.getCliente() != null) {
            dto.setCliente(new ClienteResumoDTO(
                    pedido.getCliente().getId(),
                    pedido.getCliente().getNome(),
                    pedido.getCliente().getEmail()
            ));
        }

        return dto;
    }

    public static PedidoResumoDTO toResumoDTO(Pedido pedido) {
        if (pedido == null) return null;
        
        return new PedidoResumoDTO(
                pedido.getId(),
                pedido.getEndereco(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getTotal() // Adicionar total no resumo também
        );
    }

    private static ItemPedidoDTO itemPedidoToDTO(ItemPedido itemPedido) {
        if (itemPedido == null) return null;
        
        return new ItemPedidoDTO(
                itemPedido.getId(),
                produtoToDTO(itemPedido.getProduto()),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getSubtotal()
        );
    }

    private static ProdutoDTO produtoToDTO(Produto produto) {
        if (produto == null) return null;
        
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getDescricao()
        );
    }
}
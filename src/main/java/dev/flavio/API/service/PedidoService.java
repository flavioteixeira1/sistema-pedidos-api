package dev.flavio.API.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.flavio.API.Entity.Clientes;
import dev.flavio.API.Entity.Pedido;
import dev.flavio.API.Entity.Produto;
import dev.flavio.API.Entity.ItemPedido;
import dev.flavio.API.Repository.ClientesRepository;
import dev.flavio.API.Repository.PedidoRepository;
import dev.flavio.API.Repository.ProdutoRepository;
import dev.flavio.API.dto.PedidoCreateDTO;
import dev.flavio.API.dto.ItemPedidoCreateDTO;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClientesRepository clienteRepository;

    // Método antigo - manter por compatibilidade (se necessário)
    @Deprecated
    public Pedido save(Pedido pedido) {
        pedido.setDataPedido(LocalDateTime.now());
        
        // Se o pedido já tem itens, garantir que estão corretamente configurados
        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
                if (item.getPrecoUnitario() == null && item.getProduto() != null) {
                    item.setPrecoUnitario(BigDecimal.valueOf(item.getProduto().getPreco()));
                }
            }
        }
        
        return repository.save(pedido);
    }

    // NOVO MÉTODO COM DTO - ATUALIZADO
    @Transactional
    public Pedido saveFromDTO(PedidoCreateDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        
        // Buscar e validar cliente
        Clientes cliente = clienteRepository.findById(pedidoDTO.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + pedidoDTO.getClienteId()));
        
        // Configurar pedido básico
        pedido.setEndereco(pedidoDTO.getEndereco());
        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);

        // Processar itens do pedido (produtos com quantidades)
        if (pedidoDTO.getItens() != null && !pedidoDTO.getItens().isEmpty()) {
            Set<ItemPedido> itens = new HashSet<>();
            
            for (ItemPedidoCreateDTO itemDTO : pedidoDTO.getItens()) {
                // Buscar e validar produto
                Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));
                
                // Criar item do pedido
                ItemPedido item = new ItemPedido();
                item.setPedido(pedido);
                item.setProduto(produto);
                item.setQuantidade(itemDTO.getQuantidade());
                item.setPrecoUnitario(BigDecimal.valueOf(produto.getPreco()));
                
                itens.add(item);
            }
            
            pedido.setItens(itens);
        }

        return repository.save(pedido);
    }

    @Transactional
    public Pedido update(Pedido pedido) {
        // Verifica se o pedido existe
        Pedido pedidoExistente = repository.findById(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualiza os campos básicos
        pedidoExistente.setEndereco(pedido.getEndereco());
        pedidoExistente.setStatus(pedido.getStatus());

        // Atualiza os itens se fornecidos
        if (pedido.getItens() != null) {
            // Limpar itens existentes
            pedidoExistente.getItens().clear();
            
            // Adicionar novos itens
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedidoExistente);
                if (item.getPrecoUnitario() == null && item.getProduto() != null) {
                    item.setPrecoUnitario(BigDecimal.valueOf(item.getProduto().getPreco()));
                }
                pedidoExistente.getItens().add(item);
            }
        }

        return repository.save(pedidoExistente);
    }

    // Método update usando DTO
    @Transactional
    public Pedido updateFromDTO(Long id, PedidoCreateDTO pedidoDTO) {
        // Buscar pedido existente
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Buscar e validar cliente
        Clientes cliente = clienteRepository.findById(pedidoDTO.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + pedidoDTO.getClienteId()));

        // Atualizar campos básicos
        pedidoExistente.setEndereco(pedidoDTO.getEndereco());
        pedidoExistente.setStatus(pedidoDTO.getStatus());
        pedidoExistente.setCliente(cliente);

        // Limpar itens existentes
        pedidoExistente.getItens().clear();

        // Adicionar novos itens
        if (pedidoDTO.getItens() != null && !pedidoDTO.getItens().isEmpty()) {
            for (ItemPedidoCreateDTO itemDTO : pedidoDTO.getItens()) {
                // Buscar e validar produto
                Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));
                
                // Criar item do pedido
                ItemPedido item = new ItemPedido();
                item.setPedido(pedidoExistente);
                item.setProduto(produto);
                item.setQuantidade(itemDTO.getQuantidade());
                item.setPrecoUnitario(BigDecimal.valueOf(produto.getPreco()));
                
                pedidoExistente.getItens().add(item);
            }
        }

        return repository.save(pedidoExistente);
    }


	@Transactional
	public Pedido partialUpdate(Long id, Map<String, Object> updates) {
    Pedido pedidoExistente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    
    // Atualizar apenas campos fornecidos
    if (updates.containsKey("endereco")) {
        pedidoExistente.setEndereco((String) updates.get("endereco"));
    }
    
    if (updates.containsKey("status")) {
        pedidoExistente.setStatus((String) updates.get("status"));
    }
    
    if (updates.containsKey("clienteId")) {
        Long clienteId = ((Number) updates.get("clienteId")).longValue();
        Clientes cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedidoExistente.setCliente(cliente);
    }
    
    // Para itens, você pode decidir se permite atualização parcial
    // ou exige substituição completa
    
    return repository.save(pedidoExistente);
	}



    @Transactional
    public void delete(Long id) {
        // Verifica se o pedido existe antes de deletar
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        repository.delete(pedido);
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public List<Pedido> buscarTodos() {
        return repository.findAll();
    }
}
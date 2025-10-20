package dev.flavio.API.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.flavio.API.Entity.Clientes;
import dev.flavio.API.Entity.Pedido;
import dev.flavio.API.Entity.Produto;
import dev.flavio.API.Entity.StatusPedido;
import dev.flavio.API.Entity.ItemPedido;
import dev.flavio.API.Repository.ClientesRepository;
import dev.flavio.API.Repository.PedidoRepository;
import dev.flavio.API.Repository.ProdutoRepository;
import dev.flavio.API.dto.PedidoCreateDTO;
import dev.flavio.API.dto.PedidoDTO;
import dev.flavio.API.dto.ItemPedidoCreateDTO;
import dev.flavio.API.dto.ClienteResumoDTO;
import dev.flavio.API.dto.ProdutoDTO;
import dev.flavio.API.dto.ItemPedidoDTO;

@Service
public class PedidoService {

    private PedidoRepository repository;
    private ProdutoRepository produtoRepository;
    private ClientesRepository clienteRepository;

    public PedidoService(PedidoRepository repository, ProdutoRepository produtoRepository, ClientesRepository clientesRepository){
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clientesRepository;
    }

    // MÉTODO PARA CONVERTER PEDIDO PARA DTO (FALTANTE)
    private PedidoDTO converterParaDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setEndereco(pedido.getEndereco());
        dto.setDataPedido(pedido.getDataPedido());
        dto.setStatus(pedido.getStatus());
        dto.setObservacoes(pedido.getObservacoes());
        dto.setTotal(pedido.getTotal());
        
        // Converter cliente para DTO
        if (pedido.getCliente() != null) {
            ClienteResumoDTO clienteDTO = new ClienteResumoDTO();
            clienteDTO.setId(pedido.getCliente().getId());
            clienteDTO.setNome(pedido.getCliente().getNome());
            clienteDTO.setEmail(pedido.getCliente().getEmail());
            dto.setCliente(clienteDTO);
        }
        
        // Converter itens para DTO
        if (pedido.getItens() != null) {
            Set<ItemPedidoDTO> itensDTO = pedido.getItens().stream()
                .map(this::converterItemParaDTO)
                .collect(Collectors.toSet());
            dto.setItens(itensDTO);
        }
        
        return dto;
    }
    
    // MÉTODO PARA CONVERTER ITEM PEDIDO PARA DTO
    private ItemPedidoDTO converterItemParaDTO(ItemPedido item) {
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(item.getId());
        dto.setQuantidade(item.getQuantidade());
        dto.setPrecoUnitario(item.getPrecoUnitario());
        dto.setSubtotal(item.getSubtotal());
        
        // Converter produto para DTO
        if (item.getProduto() != null) {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setId(item.getProduto().getId());
            produtoDTO.setNome(item.getProduto().getNome());
            produtoDTO.setPreco(item.getProduto().getPreco());
            produtoDTO.setDescricao(item.getProduto().getDescricao());
            dto.setProduto(produtoDTO);
        }
        
        return dto;
    }

    // MÉTODO saveFromDTO CORRIGIDO
    @Transactional
    public PedidoDTO saveFromDTO(PedidoCreateDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        
        // Buscar e validar cliente
        Clientes cliente = clienteRepository.findById(pedidoDTO.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + pedidoDTO.getClienteId()));
        
        // Configurar pedido básico
        pedido.setEndereco(pedidoDTO.getEndereco());
        pedido.setStatus(pedidoDTO.getStatus() != null ? pedidoDTO.getStatus() : StatusPedido.PENDENTE);
        pedido.setObservacoes(pedidoDTO.getObservacoes());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);

        // Processar itens do pedido
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

        Pedido pedidoSalvo = repository.save(pedido);
        return converterParaDTO(pedidoSalvo);
    }

    // MÉTODO updateFromDTO CORRIGIDO
    @Transactional
    public PedidoDTO updateFromDTO(Long id, PedidoCreateDTO pedidoDTO) {
        // Buscar pedido existente
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Buscar e validar cliente
        Clientes cliente = clienteRepository.findById(pedidoDTO.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + pedidoDTO.getClienteId()));

        // Atualizar campos básicos
        pedidoExistente.setEndereco(pedidoDTO.getEndereco());
        pedidoExistente.setStatus(pedidoDTO.getStatus());
        pedidoExistente.setObservacoes(pedidoDTO.getObservacoes());
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

        Pedido pedidoAtualizado = repository.save(pedidoExistente);
        return converterParaDTO(pedidoAtualizado);
    }

    // MÉTODO alterarStatus CORRIGIDO E COMPLETO
    @Transactional
    public PedidoDTO alterarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = repository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        // Usar o método alterarStatus da entidade Pedido
        pedido.alterarStatus(novoStatus);
        
        Pedido pedidoAtualizado = repository.save(pedido);
        return converterParaDTO(pedidoAtualizado);
    }

    // MÉTODO PARA BUSCAR POR ID RETORNANDO DTO
    public PedidoDTO findByIdDTO(Long id) {
        Pedido pedido = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return converterParaDTO(pedido);
    }

    // MÉTODO PARA LISTAR TODOS RETORNANDO DTOs
    public List<PedidoDTO> findAllDTO() {
        return repository.findAll().stream()
            .map(this::converterParaDTO)
            .collect(Collectors.toList());
    }

    // MÉTODOS ORIGINAIS (mantidos para compatibilidade)
    @Transactional
    public Pedido save(Pedido pedido) {
        pedido.setDataPedido(LocalDateTime.now());
        
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

    @Transactional
    public Pedido update(Pedido pedido) {
        Pedido pedidoExistente = repository.findById(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedidoExistente.setEndereco(pedido.getEndereco());
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setObservacoes(pedido.getObservacoes());

        if (pedido.getItens() != null) {
            pedidoExistente.getItens().clear();
            
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

    @Transactional
    public void delete(Long id) {
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
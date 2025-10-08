package dev.flavio.API.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.flavio.API.Entity.Clientes;
import dev.flavio.API.Entity.Pedido;
import dev.flavio.API.Repository.ClientesRepository;
import dev.flavio.API.Repository.PedidoRepository;

@Service
public class ClientesService {

    private ClientesRepository repository;
    private PedidoRepository pedidoRepository;

    // Construtor corrigido - removida a injeção da entidade Clientes
    public ClientesService(ClientesRepository repository, PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
    }

    public Clientes save(Clientes cliente) throws Exception {
        return repository.save(cliente);
    }

    public Clientes update(Clientes cliente) throws Exception {
        // Verifica se o cliente existe
        Clientes clienteExistente = repository.findById(cliente.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualiza os campos
        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setDataCadastro(cliente.getDataCadastro());
       
        // Atualiza os pedidos se fornecidos
        if (cliente.getPedidos() != null && !cliente.getPedidos().isEmpty()) {
            Set<Pedido> pedidos = new HashSet<>();
            cliente.getPedidos().forEach(pedido -> {
                // Usando findById em vez de getById (que está deprecated)
                pedidoRepository.findById(pedido.getId()).ifPresent(pedidos::add);
            });
            clienteExistente.setPedidos(pedidos);
        }

        return repository.save(clienteExistente);
    }

    public Clientes findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    

    public List<Clientes> findAll() {
        return repository.findAll();
    }

    public List<Clientes> findByNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public void delete(Long id) {
        // Verifica se o cliente existe antes de deletar
        Clientes cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repository.delete(cliente);
    }
}
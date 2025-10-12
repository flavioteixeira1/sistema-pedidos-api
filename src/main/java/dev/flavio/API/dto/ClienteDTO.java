package dev.flavio.API.dto;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.stream.Collectors;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
    private List<PedidoResumoDTO> pedidos;

    // Construtores
    public ClienteDTO() {}

    public ClienteDTO(Long id, String nome, String email, String telefone, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public List<PedidoResumoDTO> getPedidos() { return pedidos; }
    public void setPedidos(List<PedidoResumoDTO> pedidos) { this.pedidos = pedidos; }
}
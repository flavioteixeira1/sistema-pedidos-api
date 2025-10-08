package dev.flavio.API.dto;


public class ClienteResumoDTO {
    private Long id;
    private String nome;
    private String email;

    public ClienteResumoDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}
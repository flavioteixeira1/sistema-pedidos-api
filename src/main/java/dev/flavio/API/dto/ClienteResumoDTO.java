package dev.flavio.API.dto;


public class ClienteResumoDTO {
    private Long id;
    private String nome;
    private String email;

    public ClienteResumoDTO(){}
    
    public ClienteResumoDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    //setters

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(Long id){
        this.id = id;
    }
}
package dev.flavio.API.dto;


public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private String descricao;

    public ProdutoDTO(Long id, String nome, Double preco, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
    public String getDescricao() { return descricao; }
}
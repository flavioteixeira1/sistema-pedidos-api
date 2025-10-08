package dev.flavio.API.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.flavio.API.Entity.Produto;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
     // Método usando query derivation do Spring Data JPA
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    // Alternativa usando @Query (opcional)
  //  @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
  //  List<Produto> findByNomeLike(@Param("nome") String nome);

}

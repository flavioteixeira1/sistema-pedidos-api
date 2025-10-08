package dev.flavio.API.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.flavio.API.Entity.Clientes;

public interface ClientesRepository extends JpaRepository <Clientes, Long>{

    List <Clientes> findByNomeContainingIgnoreCase(String nome);


}

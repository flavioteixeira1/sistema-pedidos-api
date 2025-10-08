package dev.flavio.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.flavio.API.Entity.Pedido;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

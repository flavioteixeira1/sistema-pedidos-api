// StatusController.java
package dev.flavio.API.Controller;

import dev.flavio.API.Entity.StatusPedido;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/status-pedidos")
public class StatusController {

    @GetMapping
    public List<StatusDTO> getTodosStatus() {
        return Arrays.stream(StatusPedido.values())
                .map(status -> new StatusDTO(status.name(), status.getDescricao()))
                .collect(Collectors.toList());
    }

    public record StatusDTO(String valor, String descricao) {}
}
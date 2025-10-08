package dev.flavio.API.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.flavio.API.Entity.Clientes;
import dev.flavio.API.service.ClientesService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteController {

    private ClientesService service;

    public ClienteController( ClientesService service){
        this.service = service;
    }

    @PostMapping(value = "/save")
	public ResponseEntity<Clientes> salvaCliente(@RequestBody Clientes cliente) throws Exception {
		cliente = service.save(cliente);
		return ResponseEntity.ok().body(cliente);
	}

    @PutMapping(value = "/{id}")
	public ResponseEntity<Clientes> alteraCliente(@PathVariable Long id, @RequestBody Clientes cliente) throws Exception {
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.ok().body(cliente);
	}

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluiCliente(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

    @GetMapping(value = "/{id}")
	public ResponseEntity<Clientes> buscaCliente(@PathVariable Long id) {
		Clientes cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}

    @GetMapping(value = "/busca-todos")
	public ResponseEntity<List<Clientes>> buscaTodosClientes() {
		List<Clientes> cliente = service.findAll();
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping(value = "/busca-por-nome/{nome}")
	public ResponseEntity<List<Clientes>> buscaProdutosPorNome(@PathVariable String nome) {
    List<Clientes> cliente = service.findByNome(nome);
    return ResponseEntity.ok().body(cliente);
    }


}

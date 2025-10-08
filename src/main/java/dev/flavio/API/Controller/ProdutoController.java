package dev.flavio.API.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.flavio.API.Entity.Produto;
import dev.flavio.API.service.ProdutoService;


@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping(value = "/save")
	public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) throws Exception {
		produto = service.save(produto);
		return ResponseEntity.ok().body(produto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> alteraProduto(@PathVariable Long id, @RequestBody Produto produto) throws Exception {
		produto.setId(id);
		produto = service.update(produto);
		return ResponseEntity.ok().body(produto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluiProduto(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscaProduto(@PathVariable Long id) {
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
	}

	@GetMapping(value = "/busca-todos")
	public ResponseEntity<List<Produto>> buscaTodosProdutos() {
		List<Produto> produtos = service.findAll();
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/busca-por-nome/{nome}")
	public ResponseEntity<List<Produto>> buscaProdutosPorNome(@PathVariable String nome) {
    List<Produto> produtos = service.findByNome(nome);
    return ResponseEntity.ok().body(produtos);
}

}
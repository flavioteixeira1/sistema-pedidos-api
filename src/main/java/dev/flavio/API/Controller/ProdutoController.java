package dev.flavio.API.Controller;

import java.util.List;


import org.springframework.http.HttpStatus;
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
import dev.flavio.API.exceptions.RecursoNaoEncontradoException;
import dev.flavio.API.service.ProdutoService;


@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	
	private ProdutoService service;
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) throws Exception {
		produto = service.save(produto);
		return ResponseEntity.ok().body(produto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> alteraProduto(@PathVariable Long id, @RequestBody Produto produto) throws Exception {
		try {
		produto.setId(id);
		produto = service.update(produto);
		return ResponseEntity.ok().body(produto);}
		catch  (RecursoNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluiProduto(@PathVariable Long id) throws Exception {
		try {
		service.delete(id);
		return ResponseEntity.noContent().build(); }
		catch (RecursoNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscaProduto(@PathVariable Long id) throws Exception{
		try {
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
		}
		catch (RecursoNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/busca-todos")
	public ResponseEntity<List<Produto>> buscaTodosProdutos() {
		List<Produto> produtos = service.findAll();
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/busca-por-nome/{nome}")
	public ResponseEntity<?> buscaProdutosPorNome(@PathVariable String nome) throws Exception {
    try{	
		List<Produto> produtos = service.findByNome(nome);
    	return ResponseEntity.ok().body(produtos);}
		catch(RecursoNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
}

}
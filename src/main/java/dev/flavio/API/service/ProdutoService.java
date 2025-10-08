package dev.flavio.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.flavio.API.Controller.ProductNullException;
import dev.flavio.API.Controller.ProductPriceException;
import dev.flavio.API.Entity.Produto;
import dev.flavio.API.Repository.ProdutoRepository;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto save(Produto produto) throws Exception {
		validarProduto(produto);
		return repository.save(produto);
	}

	public Produto update(Produto produto) throws Exception {
		// Verifica se o produto existe
		Produto produtoExistente = repository.findById(produto.getId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		// Valida os dados do produto
		validarProduto(produto);

		// Atualiza os campos
		produtoExistente.setNome(produto.getNome());
		produtoExistente.setPreco(produto.getPreco());
		produtoExistente.setDescricao(produto.getDescricao());

		return repository.save(produtoExistente);
	}

	public void delete(Long id) {
		// Verifica se o produto existe antes de deletar
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		repository.delete(produto);
	}

	private void validarProduto(Produto produto) throws Exception {
		if (produto.getNome() == null) {
			throw new ProductNullException();
		}

		if (produto.getPreco() == null || produto.getPreco() < 0) {
			throw new ProductPriceException();
		}
	}

	public Produto findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public List<Produto> findByNome(String nome) {
    return repository.findByNomeContainingIgnoreCase(nome);
	}


}
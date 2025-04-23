package com.mwcc.crudclientes.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.mwcc.crudclientes.model.Produto;

public interface ProdutoService {
	
	List<Produto> listarTodos();
	Optional<Produto> buscaPorId(Long id);
	Produto criar(Produto produto);
	Produto atualizar(Long id, Produto produto);
	void deletar(Long id);
	Produto atualizacaoParcial(Long id, Produto produto);
	List<Produto> filtrarProdutos(String nome, String descricao, BigDecimal preco);

}

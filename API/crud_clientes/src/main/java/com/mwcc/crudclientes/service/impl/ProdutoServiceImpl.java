package com.mwcc.crudclientes.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mwcc.crudclientes.model.Produto;
import com.mwcc.crudclientes.repository.ProdutoRepository;
import com.mwcc.crudclientes.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	private ProdutoRepository produtoRepository;
	
	@Override
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	@Override
	public Optional<Produto> buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Produto criar(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto atualizar(Long id, Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto atualizacaoParcial(Long id, Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> filtrarProdutos(String nome, String descricao, BigDecimal preco) {
		// TODO Auto-generated method stub
		return null;
	}

}

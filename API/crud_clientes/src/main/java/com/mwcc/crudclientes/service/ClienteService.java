package com.mwcc.crudclientes.service;

import java.util.List;
import java.util.Optional;

import com.mwcc.crudclientes.model.Cliente;

public interface ClienteService {
	
	List<Cliente> listarTodos();
	Optional<Cliente> buscarPorId(Long id);
	Cliente criar(Cliente cliente);
	Cliente atualizar(Long id, Cliente cliente);
	void deletar(Long id);
	Cliente atualizacaoParcial(Long id, Cliente cliente);
	List<Cliente> filtrarClientes(String nome, String email, String telefone);

}

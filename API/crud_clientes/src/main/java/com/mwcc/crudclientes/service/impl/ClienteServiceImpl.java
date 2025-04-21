package com.mwcc.crudclientes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwcc.crudclientes.model.Cliente;
import com.mwcc.crudclientes.repository.ClienteRepository;
import com.mwcc.crudclientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente criar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente atualizar(Long id, Cliente novoCliente) {
		return clienteRepository.findById(id).map(cliente -> {
			cliente.setNome(novoCliente.getNome());
			cliente.setEmail(novoCliente.getEmail());
			cliente.setTelefone(novoCliente.getTelefone());
			return clienteRepository.save(cliente);
		}).orElseThrow(() -> new RuntimeException("Cliente nao encontrado com o id: " + id));
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
		
	}

	@Override
	public Cliente atualizacaoParcial(Long id, Cliente clienteParcial) {
		return clienteRepository.findById(id).map(cliente -> {
			if(clienteParcial.getNome() != null) {
				cliente.setNome(clienteParcial.getNome());
			}
			if(clienteParcial.getEmail() != null) {
				cliente.setEmail(clienteParcial.getEmail());
			}
			if(clienteParcial.getTelefone() != null) {
				cliente.setTelefone(clienteParcial.getTelefone());
			}
			
			return clienteRepository.save(cliente);
		}).orElseThrow(() -> new RuntimeException("Cliente nao encontrado com o id: " + id));
	}

	@Override
	public List<Cliente> filtrarClientes(String nome, String email, String telefone) {
		return clienteRepository.filtrarClientes(nome, email, telefone);
	}

	
	
}

package com.mwcc.crudclientes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mwcc.crudclientes.dto.ClienteDTO;
import com.mwcc.crudclientes.model.Cliente;
import com.mwcc.crudclientes.repository.ClienteRepository;
import com.mwcc.crudclientes.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listarTodos(){
		List<Cliente> retorno = clienteService.listarTodos();
		return retorno;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Long id){
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		return cliente.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<?> filtrarClientes(@RequestParam(required = false) String nome,
										@RequestParam(required = false) String email,
										@RequestParam(required = false) String telefone){
		List<Cliente> result = clienteService.filtrarClientes(nome, email, telefone);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public Cliente criar(@RequestBody Cliente cliente) {
		return clienteService.criar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable("id") Long id, @RequestBody Cliente dadosAtualizados){
		Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);
		
		if(clienteExistente.isPresent()) {
			Cliente cliente = clienteExistente.get();
			cliente.setNome(dadosAtualizados.getNome());
			cliente.setEmail(dadosAtualizados.getEmail());
			cliente.setTelefone(dadosAtualizados.getTelefone());
			return ResponseEntity.ok(clienteService.criar(cliente));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		if(clienteRepository.existsById(id)) {
			clienteService.deletar(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Cliente> atualizacaoParcial(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO){
		Optional<Cliente> clienteOptional = clienteService.buscarPorId(id);
		if(clienteOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = clienteOptional.get();
		
		if(clienteDTO.getNome() != null){
			cliente.setNome(clienteDTO.getNome());
		}
		
		if(clienteDTO.getEmail() != null) {
			cliente.setEmail(clienteDTO.getEmail());
		}
		
		if(clienteDTO.getTelefone() != null) {
			cliente.setTelefone(clienteDTO.getTelefone());
		}
		
		Cliente clienteAtualizado = clienteService.criar(cliente);
		
		return ResponseEntity.ok(clienteAtualizado);
			
	}
	
}

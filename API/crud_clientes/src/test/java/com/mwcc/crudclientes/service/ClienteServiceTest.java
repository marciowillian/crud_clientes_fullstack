package com.mwcc.crudclientes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mwcc.crudclientes.model.Cliente;
import com.mwcc.crudclientes.repository.ClienteRepository;
import com.mwcc.crudclientes.service.impl.ClienteServiceImpl;

public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private ClienteServiceImpl clienteService;
	
	private Cliente cliente;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		cliente = Cliente.builder()
				.id(1L)
				.nome("João")
				.email("joao@email.com")
				.telefone("123456789")
				.build();
	}
	
	@Test
	public void testListarTodos() {
		List<Cliente> clientes = List.of(cliente);
		when(clienteRepository.findAll()).thenReturn(clientes);
		List<Cliente> result = clienteService.listarTodos();
		
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		verify(clienteRepository, times(1)).findAll();
	}
	
	@Test
	void testBuscaPorId() {
		when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		Optional<Cliente> result = clienteService.buscarPorId(1L);
		
		assertTrue(result.isPresent());
		assertEquals("João" ,result.get().getNome());
		verify(clienteRepository).findById(1L);
	}
	
	@Test
	void testCriarCliente() {
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		Optional<Cliente> result = Optional.of(clienteService.criar(cliente));
		
		assertTrue(result.isPresent());
		assertEquals("João", result.get().getNome());
		verify(clienteRepository, times(1)).save(cliente);
		verify(clienteRepository).save(cliente);
	}
	
	@Test
	void testAtualizarCliente() {
		Cliente clienteAtualizado = Cliente.builder()
				.nome("Paulo").build();
		when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		when(clienteRepository.save(cliente)).thenReturn(clienteAtualizado);
		when(clienteService.atualizar(1L, clienteAtualizado)).thenReturn(clienteAtualizado);
		Cliente result = clienteService.atualizar(1L, clienteAtualizado);
		
		
//		assertFalse(clienteAtualizado.getNome() == cliente.getNome());
//		assertTrue(result.getNome() == "Paulo");
		verify(clienteRepository, times(2)).findById(1L);
		verify(clienteRepository, times(1)).save(cliente);
	}
	
}

package com.mwcc.crudclientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mwcc.crudclientes.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE " +
	"(:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
	"(:email IS NULL OR LOWER(c.email) = LOWER(:email)) AND " +
	"(:telefone IS NULL OR c.telefone = :telefone)")
	List<Cliente> filtrarClientes(
			@Param("nome") String nome,
			@Param("email") String email,
			@Param("telefone") String telefone);

}

package com.mwcc.crudclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mwcc.crudclientes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

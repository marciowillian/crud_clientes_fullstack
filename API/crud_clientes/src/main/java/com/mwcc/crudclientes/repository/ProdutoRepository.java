package com.mwcc.crudclientes.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mwcc.crudclientes.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE " +
			"(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND "
			+ "(:descricao IS NULL OR LOWER(p.descricao) = LOWER(:descricao)) AND "
			+ "(:preco IS NULL OR p.preco = :preco)")
	List<Produto> filtrarProdutos(@Param("nome") String nome,
			@Param("descricao") String descricao,
			@Param("preco") BigDecimal preco);
	
}

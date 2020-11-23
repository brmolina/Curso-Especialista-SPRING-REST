package com.algaworks.algafood.domain.repository;

import java.beans.JavaBean;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository 
extends CustomJpaRepository<Restaurante, Long>, RestautanteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{
	
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :cozinha")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}

package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.jdbc.core.ResultSetSupportingSqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestautanteRepositoryQueries;

import lombok.var;

@Repository
public class RestauranteRepositoryImpl implements RestautanteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);

		Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
		Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
		
		criteria.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);
		
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
		
		/*
		 * var jpql = new StringBuilder(); jpql.append("from Restaurante where 0=0 ");
		 * 
		 * var parametros = new HashMap<String, Object>();
		 * 
		 * if(StringUtils.hasLength(nome)) { jpql.append("and nome like :nome ");
		 * parametros.put("nome", "%" + nome + "%"); }
		 * 
		 * if(taxaFreteInicial != null) { jpql.append("and taxaFrete >= :taxaInicial ");
		 * parametros.put("taxaInicial", taxaFreteInicial); }
		 * 
		 * if(taxaFreteFinal != null) { jpql.append("and taxaFrete <= :taxaFinal ");
		 * parametros.put("taxaFinal", taxaFreteFinal); }
		 * 
		 * TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(),
		 * Restaurante.class);
		 * 
		 * parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		 * 
		 * return query.getResultList();
		 */
	}
}

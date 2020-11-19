package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNome(
			String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
		  
		  
	  }
		/*
		 * @GetMapping("/restaurantes/por-taxa-frete") public List<Restaurante>
		 * restaurantesPorTaxaFrete (BigDecimal taxaInicial, BigDecimal taxaFinal){
		 * return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
		 * }
		 */
	  
	  
}
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
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;

import lombok.var;

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
		
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante>restaurantesPorNomeFrete (String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
		}
		 
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante>restaurantesComFreteGratis (String nome){
		
		var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		
		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
		}
	  
	  
}
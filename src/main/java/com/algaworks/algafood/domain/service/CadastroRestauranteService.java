package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		cozinhaRepository.findById(cozinhaId)
		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cozinha com id %d não existe", cozinhaId)));
		
		return restauranteRepository.save(restaurante);
	}
	
	public void excluir(Long restauranteId) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
		
		if(!restaurante.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante com id %d não encontrado", restauranteId));
		}
		restauranteRepository.deleteById(restauranteId);
		
	}
}

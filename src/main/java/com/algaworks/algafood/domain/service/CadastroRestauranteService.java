package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante com id %d não encontrado";

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = buscarOuFalharCozinha(restaurante);
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public void excluir(Long restauranteId) {
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		try {
		restauranteRepository.deleteById(restauranteId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de id %d não pode ser excluido pois está em uso", restauranteId));
		}
		
	}
	
	public Restaurante buscarOuFalhar (Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(
						String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
		
	}
	
	public Cozinha buscarOuFalharCozinha(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		return cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new RestauranteNaoEncontradoException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
	}
}

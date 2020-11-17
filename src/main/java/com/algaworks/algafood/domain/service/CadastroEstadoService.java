package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado){
		try {
			return estadoRepository.salvar(estado);
		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de id %d não encontrado", estado.getId()));
		}
	}
	
	public void excluir(Long estadoId) {
		Estado estado = estadoRepository.buscar(estadoId);
		if(estado==null) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de id %d não encontrado", estadoId));
		} try {
			estadoRepository.remover(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado de id %d não pode ser removido pois está em uso", estadoId));
		}
	}
	
}

package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		if(!estado.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de id %d não encontrado", estadoId));
		}
		//String nomeEstado = estado.getNome();
		try {
			return cidadeRepository.save(cidade);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", cidade.getId()));
		} 
	}
	
	public void excluir(Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		if(!cidade.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade de id %d não encontrada", cidadeId));
		}
		cidadeRepository.deleteById(cidadeId);
	}
	
}

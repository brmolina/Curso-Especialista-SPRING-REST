package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado de id %d não encontrado";

	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Cidade de id %d não encontrada";

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	CadastroEstadoService cadastroEstado;
	
	public Cidade salvar(Cidade cidade) {
		long estadoId = cidade.getEstado().getId();
		
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
			
	}
	
	public void excluir(Long cidadeId) {
		Cidade cidade = buscarOuFalhar(cidadeId);
		try {
			cidadeRepository.deleteById(cidadeId);
		} 
		
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade não pode ser excluida pois está em uso", cidadeId));
		}
	}
	
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return  cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
	
	
}

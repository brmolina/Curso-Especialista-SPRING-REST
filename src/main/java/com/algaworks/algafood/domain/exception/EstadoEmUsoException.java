package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND/* , reason = "Entidade não encontrada" */)
public class EstadoEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EstadoEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoEmUsoException(Long estadoId) {
		this(String.format("Estado de código %d não pode ser excluído pois está em uso", estadoId));
	}
}

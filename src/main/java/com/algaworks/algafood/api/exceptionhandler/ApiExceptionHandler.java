package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(
			EntidadeNaoEncontradaException e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(
			NegocioException e) {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
	}
	
	/*
	 * @ExceptionHandler(HttpMediaTypeNotSupportedException.class) public
	 * ResponseEntity<?> handleHttpMediaTypeNotSuportedException() { Problema
	 * problema = Problema.builder() .dataHora(LocalDateTime.now())
	 * .mensagem("O tipo de mídia não é aceito").build();
	 * 
	 * return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	 * .body(problema); }
	 */
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException() {
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem("Registro não pode ser excluido pois está em uso").build();
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(problema);
	}
}



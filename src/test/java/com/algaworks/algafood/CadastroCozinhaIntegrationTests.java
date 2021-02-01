package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

		@Test
		public void testarCadastroCozinhaComSucesso() {
			//cenario
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome("Chinesa");
			
			//acao
			novaCozinha = cadastroCozinha.salvar(novaCozinha);
			
			//validação
			assertThat(novaCozinha).isNotNull();
			assertThat(novaCozinha.getId()).isNotNull();
		}
		
		@Test(expected = ConstraintViolationException.class)
		public void testarCadastrarCozinhaSemNome() {
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome(null);
			
			novaCozinha = cadastroCozinha.salvar(novaCozinha);
		}
		
	

}

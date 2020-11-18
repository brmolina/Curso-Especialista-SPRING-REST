package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.tools.reflect.Reflection;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
		if(restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	//@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar (@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
		if (restauranteAtual.isPresent()) {
			try {
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
				
				Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restauranteSalvo);
			} catch (EntidadeNaoEncontradaException e){
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		} return ResponseEntity.notFound().build();
	}
	
	/*
	 * @PatchMapping("/{restauranteId}") public ResponseEntity<?>
	 * atualizarParcial(@PathVariable Long restauranteId,
	 * 
	 * @RequestBody Map<String, Object> campos){
	 * 
	 * Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
	 * 
	 * if(restauranteAtual == null) { return ResponseEntity.notFound().build(); }
	 * 
	 * merge(campos, restauranteAtual); return atualizar(restauranteId,
	 * restauranteAtual); }
	 */

	/*
	 * private void merge(Map<String, Object> dadosOrigem, Restaurante
	 * restauranteDestino) {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper(); Restaurante restauranteOrigem
	 * = objectMapper.convertValue(dadosOrigem, Restaurante.class);
	 * 
	 * dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> { Field field =
	 * ReflectionUtils.findField(Restaurante.class, nomePropriedade);
	 * field.setAccessible(true);
	 * 
	 * Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
	 * 
	 * System.out.println(nomePropriedade+" = "+valorPropriedade+" = "+novoValor);
	 * 
	 * ReflectionUtils.setField(field, restauranteDestino, novoValor); }); }
	 */
	
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<?> excluir(@PathVariable Long restauranteId){
		try {
			cadastroRestaurante.excluir(restauranteId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}

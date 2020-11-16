package com.algaworks.algafood.domain.repository;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	 List<Restaurante> listar();
	 
	 Restaurante buscar(Long id);
	 
	 Restaurante salvar(Restaurante restaurante);
	 
	 void remover(Long id);
}

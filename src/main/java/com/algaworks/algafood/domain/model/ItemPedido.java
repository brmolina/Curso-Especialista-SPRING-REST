package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@Column(nullable = false)
	private BigDecimal precoTotal;
	
	private String observacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

}

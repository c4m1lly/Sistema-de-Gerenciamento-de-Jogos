package com.generation.xpgames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_jogos")
public class Jogos {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Esse campo é obrigatório!")
	@Size(min = 3, max = 1000, message = "digite no minimo 3 letras e no maximo 100!") 
	private String nomeDoJogo;
	
	@NotNull(message = "O atributo preço é obrigatório!")
	private BigDecimal preco;
	
	@NotBlank(message = "O campo descrição é obrigatorio!")
	@Size(min = 3, max = 1000, message = "digite no minimo 3 letras e no maximo 1000!")
	private String descricao;
	
	@NotBlank(message = "O campo plataforma é obrigatório!")
	@Size(min = 3, max = 100, message = "digite no minimo 3 letras e no maximo 100!")
	private String plataforma;
	
	@NotBlank(message = "O campo Formato é obrigatório")
	@Size(min = 3, max = 100, message = "digite no minimo 3 letras e no maximo 100!")
	private String formato;
	
	@ManyToOne
	@JsonIgnoreProperties("jogos")
	private Categoria categoria;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDoJogo() {
		return nomeDoJogo;
	}

	public void setNomeDoJogo(String nomeDoJogo) {
		this.nomeDoJogo = nomeDoJogo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	// INDICA QUE QUE NA TABELA POSTAGEM TERA UM CAMPO CHAMADO TEMA QUE É UMA REFERENCIA AO OBJETO TEMA
	
}

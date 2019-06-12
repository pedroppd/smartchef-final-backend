package com.pedrodantas.smartchef.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.pedrodantas.smartchef.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Campo obrigatório")
	private String descricao;
	

	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria c) {
		this.id = c.getId();
		this.descricao = c.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}
	
	
	
	
	

}

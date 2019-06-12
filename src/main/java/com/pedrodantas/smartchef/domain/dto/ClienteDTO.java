package com.pedrodantas.smartchef.domain.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.pedrodantas.smartchef.domain.Cliente;
import com.pedrodantas.smartchef.service.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Campo obrigatório")
	@Length(min=5, max=30, message="O tamanho deve ser entre 5 e 30 caracteres")
	private String nome;
	
	@NotEmpty(message="Campo obrigatório")
	private String cpf;
	
	@NotEmpty(message="Campo obrigatório")
	@Column(unique=true)
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		email = obj.getEmail();	
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

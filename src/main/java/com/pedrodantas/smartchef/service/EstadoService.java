package com.pedrodantas.smartchef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.Estado;
import com.pedrodantas.smartchef.repository.EstadoRepository;
import com.pedrodantas.smartchef.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	//return all states of database
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}
	
	//return states by id
	public Estado findById(Integer id) {
		Optional<Estado> obj = estadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Estado não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
}

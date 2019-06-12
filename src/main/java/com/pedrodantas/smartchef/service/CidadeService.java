package com.pedrodantas.smartchef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.Cidade;
import com.pedrodantas.smartchef.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	// return all city's
	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}

	
	public List<Cidade> findByEstado(Integer estado_id){
		List<Cidade> cidade = cidadeRepository.findCidade(estado_id);
		return cidade;
	}


	// insert an city in db
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		obj = cidadeRepository.save(obj);
		return obj;
	}

	
	
	
	
	//Method for help the update city
	public void updateData(Cidade newObj, Cidade obj) {
		newObj.setNome(obj.getNome());
		newObj.setEstado(obj.getEstado());
	}

}

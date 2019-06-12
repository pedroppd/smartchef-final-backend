package com.pedrodantas.smartchef.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedrodantas.smartchef.domain.Cidade;
import com.pedrodantas.smartchef.domain.Estado;
import com.pedrodantas.smartchef.domain.dto.CidadeDTO;
import com.pedrodantas.smartchef.domain.dto.EstadoDTO;
import com.pedrodantas.smartchef.service.CidadeService;
import com.pedrodantas.smartchef.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	//return all states of database
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> obj =  estadoService.findAll();
		List<EstadoDTO> objDTO = obj.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}
	
	//return state by id
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Integer id){
		Estado obj = estadoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@RequestMapping(method=RequestMethod.GET, value="/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	
}

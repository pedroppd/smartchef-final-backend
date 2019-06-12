package com.pedrodantas.smartchef.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrodantas.smartchef.domain.Cidade;
import com.pedrodantas.smartchef.service.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cidadeService;
	
	//return all city's from database
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findAll(){
		List<Cidade> cidades =  cidadeService.findAll();
		return ResponseEntity.ok().body(cidades);
	}
	
	
	
	//insert an city in db 
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Cidade obj){
		 obj = cidadeService.insert(obj);
		 // return the uri from city
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		 .path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	}
	
	
	
}

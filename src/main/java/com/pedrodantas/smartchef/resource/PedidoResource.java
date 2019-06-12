package com.pedrodantas.smartchef.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrodantas.smartchef.domain.Pedido;
import com.pedrodantas.smartchef.service.PedidoService;


@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	//return an list of request
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> pedidos =  pedidoService.findAll();  
		return ResponseEntity.ok().body(pedidos);
	}
	
	
	//return one request by id
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Pedido> findbyId(@PathVariable Integer id){
		Pedido obj = pedidoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//insert one request
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}

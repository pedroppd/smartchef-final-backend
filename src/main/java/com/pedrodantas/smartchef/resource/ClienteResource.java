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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrodantas.smartchef.domain.Cliente;
import com.pedrodantas.smartchef.domain.dto.ClienteNewDTO;
import com.pedrodantas.smartchef.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	//return all clientes from db
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok().body(clientes);
	}
	
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value="value") String email) {
		Cliente obj = clienteService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	//return cliente by id
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id){
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	//Method for insert one client in db
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objNewDTO){
		Cliente obj = clienteService.fromDTO(objNewDTO);
		obj.setId(null);
		clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//method for update one client
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteNewDTO objNewDTO, @PathVariable Integer id){
		Cliente obj = clienteService.fromDTO(objNewDTO);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

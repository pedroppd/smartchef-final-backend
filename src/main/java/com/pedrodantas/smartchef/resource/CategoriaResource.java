package com.pedrodantas.smartchef.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrodantas.smartchef.domain.Categoria;
import com.pedrodantas.smartchef.domain.dto.CategoriaDTO;
import com.pedrodantas.smartchef.service.CategoriaService;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	//return an list of categoryDTO
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list =  categoriaService.findAll(); 
		//Pass an category for an CategoryDTO
		List<CategoriaDTO> objDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}
	
	
	//return category by id
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Categoria> findbyId(@PathVariable Integer id){
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//insert one new object in db
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj = categoriaService.insert(obj);
		//return the URI from new object created
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    		.path("/{id}").buildAndExpand(obj.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}
	
	//method for update an category
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//method for delete an category
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	//method for return an list paginate of category's
	@RequestMapping(method=RequestMethod.GET, value="/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj  -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	
	
	
	
}

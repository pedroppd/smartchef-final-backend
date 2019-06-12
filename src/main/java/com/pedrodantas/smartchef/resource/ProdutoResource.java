package com.pedrodantas.smartchef.resource;

import java.net.URI;
import java.util.List;

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

import com.pedrodantas.smartchef.domain.Produto;
import com.pedrodantas.smartchef.domain.dto.ProdutoDTO;
import com.pedrodantas.smartchef.resource.utils.URL;
import com.pedrodantas.smartchef.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
/*
	// return all products from DB
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> obj = produtoService.findAll();
		return ResponseEntity.ok().body(obj);
	}
*/
	// return one product by id from DB
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// method for insert one product in db
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO objDTO) {
		Produto obj = produtoService.fromDTO(objDTO);
		produtoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	// update one product from DATABASE
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO objDTO, @PathVariable Integer id) {
		Produto obj = produtoService.fromDTO(objDTO);
		obj.setId(id);
		obj = produtoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	// method for delete one product from DATABASE
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// method for return an list paginate of product's
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}

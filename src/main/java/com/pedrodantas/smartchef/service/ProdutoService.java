package com.pedrodantas.smartchef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.Categoria;
import com.pedrodantas.smartchef.domain.Produto;
import com.pedrodantas.smartchef.domain.dto.ProdutoDTO;
import com.pedrodantas.smartchef.repository.CategoriaRepository;
import com.pedrodantas.smartchef.repository.ProdutoRepository;
import com.pedrodantas.smartchef.service.exception.DataIntegrityException;
import com.pedrodantas.smartchef.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	// return all category
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	// return category by id
	public Produto findById(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	// insert an new category in DB
	public Produto insert(Produto obj) {
		return produtoRepository.save(obj);
	}

	// update an product
	public Produto update(Produto obj) {
		Produto newObj = findById(obj.getId()); // compare an category with another category
		updateData(newObj, obj);
		return produtoRepository.save(newObj);
	}

	// prepare one new object for insert in db
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
		newObj.setCategorias(obj.getCategorias());
		newObj.setItens(obj.getItens());
		newObj.setPreco(obj.getPreco());
	}

	// method for delete an product
	public void delete(Integer id) {
		try {
			findById(id);
			produtoRepository.deleteById(id);
		} catch (DataIntegrityException ex) {
			throw new DataIntegrityException("Não é possível excluir uma produto que possui produtos");
		}
	}

	// list all product by lines
	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return produtoRepository.findAll(pageRequest);
	}

	// pass one DTO and after return an product
	public Produto fromDTO(ProdutoDTO objDTO) {
		return new Produto(objDTO.getId(), objDTO.getNome(), objDTO.getPreco());
	}
	
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}

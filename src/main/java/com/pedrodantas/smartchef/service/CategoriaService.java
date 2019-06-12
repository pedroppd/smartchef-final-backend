package com.pedrodantas.smartchef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pedrodantas.smartchef.domain.Categoria;
import com.pedrodantas.smartchef.domain.dto.CategoriaDTO;
import com.pedrodantas.smartchef.repository.CategoriaRepository;
import com.pedrodantas.smartchef.service.exception.DataIntegrityException;
import com.pedrodantas.smartchef.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	// return all category
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	// return category by id
	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	// insert an new category in DB
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	// update an category
	public Categoria update(Categoria obj) {
		Categoria newObj = findById(obj.getId()); // compare an category with another category
		updateData(newObj, obj);
		return categoriaRepository.save(newObj);
	}

	// prepare one new object for insert in db
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setDescricao(obj.getDescricao());
	}

	// method for delete an category
	public void delete(Integer id) {
		try {
			findById(id);
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityException ex) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	//list all category's by lines
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,  Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	
	//pass one DTO and after return an category
	public Categoria fromDTO(CategoriaDTO objDTO){
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	

}

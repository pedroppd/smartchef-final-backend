package com.pedrodantas.smartchef.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedrodantas.smartchef.domain.Cidade;
import com.pedrodantas.smartchef.domain.Cliente;
import com.pedrodantas.smartchef.domain.dto.ClienteNewDTO;
import com.pedrodantas.smartchef.repository.ClienteRepository;
import com.pedrodantas.smartchef.service.exception.DataIntegrityException;
import com.pedrodantas.smartchef.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private S3Service s3service;
	

	// Return all client from db
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

	// return client by id
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	// return client by email
	public Cliente findByEmail(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado ou não existe: " + email);
		}
		return cliente;
	}

	// Method for insert one client in db
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		return obj;
	}

	// method for update the client
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setTelefones(obj.getTelefones());
		newObj.setPedidos(obj.getPedidos());
		newObj.setCpf(obj.getCpf());
		newObj.setEmail(obj.getEmail());
	}

	// method for delete
	public void delete(Integer id) {
		try {
			findById(id);
			clienteRepository.deleteById(id);
		} catch (DataIntegrityException ex) {
			throw new DataIntegrityException("Não é possível excluir um cliente com outra entidades atreladas");
		}
	}
	
	
	// list all client's by lines
		public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return clienteRepository.findAll(pageRequest);
		}

		// pass one DTO and after return an client
		public Cliente fromDTO(ClienteNewDTO objNewDTO) {
			Cliente cli = new  Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpf());
			Cidade cid = new Cidade(objNewDTO.getCidadeId(), null, null);
			cli.getTelefones().addAll(Arrays.asList(objNewDTO.getTelefone1()));
			if (objNewDTO.getTelefone2()!=null) {
				cli.getTelefones().add(objNewDTO.getTelefone2());
			}
			if (objNewDTO.getTelefone3()!=null) {
				cli.getTelefones().add(objNewDTO.getTelefone3());
			}
			return cli;
			
		}
		
		
}

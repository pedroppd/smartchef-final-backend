package com.pedrodantas.smartchef.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pedrodantas.smartchef.domain.Cliente;
import com.pedrodantas.smartchef.domain.dto.ClienteNewDTO;
import com.pedrodantas.smartchef.repository.ClienteRepository;
import com.pedrodantas.smartchef.resource.exception.FieldMessage;
import com.pedrodantas.smartchef.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.equals(!BR.isValidCPF(objDto.getCpf()))) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}


		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getFieldMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
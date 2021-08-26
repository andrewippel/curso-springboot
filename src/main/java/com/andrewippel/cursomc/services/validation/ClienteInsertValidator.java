package com.andrewippel.cursomc.services.validation;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.andrewippel.cursomc.domain.Cliente;
import com.andrewippel.cursomc.dto.ClienteNewDTO;
import com.andrewippel.cursomc.enums.TipoCliente;
import com.andrewippel.cursomc.repositories.ClienteRepository;
import com.andrewippel.cursomc.resources.exceptions.FieldMessage;
import com.andrewippel.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo() == TipoCliente.PESSOA_FISICA.getCod() && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
        }
        if (objDto.getTipo() == TipoCliente.PESSOA_JURIDICA.getCod() && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente na base."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
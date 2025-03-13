package com.les.carest.service;

import com.les.carest.model.Cliente;
import com.les.carest.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "ClienteService", description = "Acesso aos métodos de Cliente")
public class ClienteService extends _GenericService<Cliente, ClienteRepository> {

    protected ClienteService(ClienteRepository repositoryGenerics) {
        super(repositoryGenerics);
    }


}

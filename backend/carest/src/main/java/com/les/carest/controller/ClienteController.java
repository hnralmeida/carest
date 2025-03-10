package com.les.carest.controller;

import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
@RequestMapping("/cliente")
public class ClienteController extends _GenericController<Cliente> {
    public ClienteController(ClienteService clienteService) {
        super(clienteService);
    }
}
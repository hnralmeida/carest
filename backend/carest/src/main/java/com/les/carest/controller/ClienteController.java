package com.les.carest.controller;

import com.les.carest.model.Cliente;
import com.les.carest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
@RequestMapping("/cliente")
public class ClienteController extends GenericController<Cliente> {
    public ClienteController(ClienteService clienteService) {
        super(clienteService);
    }
}